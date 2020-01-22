package com.miui.gallery.card.scenario;

import com.miui.gallery.card.Card;
import com.miui.gallery.card.preprocess.ScenarioAlbumTask;
import com.miui.gallery.card.preprocess.ScenarioTask;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.AssistantScenarioStrategy;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.dao.base.Entity;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.assistant.FlagUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import org.json.JSONObject;

public class ScenarioTrigger {
    private int mFreeScenarioTriggerInterval = 6;
    private final List<Scenario> mFreeScenarios = new LinkedList();
    private final List<Scenario> mGuaranteeScenarios = new LinkedList();
    private final TreeSet<Scenario> mNormalScenarios = new TreeSet<>();

    public ScenarioTrigger() {
        AssistantScenarioStrategy assistantScenarioStrategy = CloudControlStrategyHelper.getAssistantScenarioStrategy();
        if (assistantScenarioStrategy != null) {
            Scenario.setDuplicateRemovalInterval(assistantScenarioStrategy.getDuplicateRemovalInterval());
            Scenario.setDefaultMinImageCount(assistantScenarioStrategy.getDefaultMinImageCount());
            Scenario.setDefaultMaxImageCount(assistantScenarioStrategy.getDefaultMaxImageCount());
            Scenario.setDefaultSelectedMinImageCount(assistantScenarioStrategy.getDefaultMinSelectedImageCount());
            Scenario.setDefaultSelectedMaxImageCount(assistantScenarioStrategy.getDefaultMaxSelectedImageCount());
            this.mFreeScenarioTriggerInterval = assistantScenarioStrategy.getFreeScenarioTriggerInterval();
            List<AssistantScenarioStrategy.ScenarioRule> localScenarioRules = assistantScenarioStrategy.getLocalScenarioRules();
            if (MiscUtil.isValid(localScenarioRules)) {
                for (AssistantScenarioStrategy.ScenarioRule createLocalScenario : localScenarioRules) {
                    Scenario createLocalScenario2 = ScenarioFactory.createLocalScenario(createLocalScenario);
                    if (createLocalScenario2 != null) {
                        if (createLocalScenario2.getFlag() == 4) {
                            this.mFreeScenarios.add(createLocalScenario2);
                        } else if (createLocalScenario2.getFlag() == 16) {
                            this.mGuaranteeScenarios.add(createLocalScenario2);
                        } else {
                            this.mNormalScenarios.add(createLocalScenario2);
                        }
                    }
                }
            }
            List<AssistantScenarioStrategy.CloudTimeScenarioRule> cloudTimeScenarioRules = assistantScenarioStrategy.getCloudTimeScenarioRules();
            if (MiscUtil.isValid(cloudTimeScenarioRules)) {
                for (AssistantScenarioStrategy.CloudTimeScenarioRule createCloudTimeScenario : cloudTimeScenarioRules) {
                    Scenario createCloudTimeScenario2 = ScenarioFactory.createCloudTimeScenario(createCloudTimeScenario);
                    if (createCloudTimeScenario2 != null) {
                        this.mNormalScenarios.add(createCloudTimeScenario2);
                    }
                }
            }
        }
    }

    private static boolean addFailedRecord(Record record, Scenario scenario) {
        record.setState(0);
        return addRecord(record);
    }

    private static boolean addRecord(Record record) {
        return GalleryEntityManager.getInstance().insert((Entity) record);
    }

    private boolean isCardGeneratedRecently(long j) {
        List<Card> query = GalleryEntityManager.getInstance().query(Card.class, "ignored = 0", (String[]) null, "createTime desc", String.format(Locale.US, "%s,%s", new Object[]{0, 1}));
        return MiscUtil.isValid(query) && DateUtils.getCurrentTimeMillis() - j < query.get(0).getCreateTime();
    }

    private void randomSort(List<Scenario> list) {
        if (MiscUtil.isValid(list)) {
            Scenario[] scenarioArr = (Scenario[]) list.toArray(new Scenario[list.size()]);
            int length = scenarioArr.length;
            Random random = new Random();
            while (length > 0) {
                int i = length - 1;
                int nextInt = random.nextInt(length);
                Scenario scenario = scenarioArr[nextInt];
                scenarioArr[nextInt] = scenarioArr[i];
                scenarioArr[i] = scenario;
                length = i;
            }
            ListIterator<Scenario> listIterator = list.listIterator();
            for (Scenario scenario2 : scenarioArr) {
                listIterator.next();
                listIterator.set(scenario2);
            }
        }
    }

    private void statScenarioTriggerFailed() {
        Log.d("ScenarioTrigger", "Scenario Trigger Failed.");
        HashMap hashMap = new HashMap();
        hashMap.put("reason", "No triggered scenario");
        GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_card_create_failed", hashMap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    private int triggerInternal(Collection<Scenario> collection, int i) {
        for (Scenario next : collection) {
            Log.d("ScenarioTrigger", "%s,scenario: %s %s start...", DateUtils.getDateFormat(DateUtils.getCurrentTimeMillis()), next.getName(), Integer.valueOf(next.getScenarioId()));
            if (FlagUtil.hasFlag(i, next.getFlag()) || !next.prepare(next.findRecords(), next.findCards())) {
                Log.d("ScenarioTrigger", "prepare failed");
            } else {
                List<Long> loadMediaItem = next.loadMediaItem();
                Record record = new Record(next, loadMediaItem);
                boolean z = false;
                if (loadMediaItem == null || loadMediaItem.size() < next.getMinImageCount()) {
                    if (loadMediaItem != null) {
                        z = loadMediaItem.size();
                    }
                    Log.d("ScenarioTrigger", "media items is too few %s", (Object) Integer.valueOf(z));
                    addFailedRecord(record, next);
                } else if (!addRecord(record)) {
                    Log.e("ScenarioTrigger", "add record error");
                } else {
                    i |= next.getFlagDisableMask();
                    Log.d("ScenarioTrigger", "Scenario %s trigger successfully. Try generate card!", (Object) next.getClass().getSimpleName());
                    ScenarioAlbumTask scenarioAlbumTask = new ScenarioAlbumTask(2);
                    if (next.getFlag() == 16) {
                        z = true;
                    }
                    if (scenarioAlbumTask.generateCard((JSONObject) null, record, z) == ScenarioAlbumTask.CardResult.HAVE_UNPROCESSED_IMAGES) {
                        ScenarioTask.post(2, String.valueOf(record.getId()), record.getId());
                    }
                }
            }
        }
        return i;
    }

    public Scenario getScenarioById(int i) {
        Iterator<Scenario> it = this.mNormalScenarios.iterator();
        while (it.hasNext()) {
            Scenario next = it.next();
            if (next.getScenarioId() == i) {
                return next;
            }
        }
        for (Scenario next2 : this.mFreeScenarios) {
            if (next2.getScenarioId() == i) {
                return next2;
            }
        }
        for (Scenario next3 : this.mGuaranteeScenarios) {
            if (next3.getScenarioId() == i) {
                return next3;
            }
        }
        return null;
    }

    public synchronized void trigger() {
        int triggerInternal = triggerInternal(this.mNormalScenarios, 0);
        if (triggerInternal == 0 && MiscUtil.isValid(this.mFreeScenarios) && !isCardGeneratedRecently(((long) this.mFreeScenarioTriggerInterval) * 86400000)) {
            ArrayList arrayList = new ArrayList(this.mFreeScenarios);
            randomSort(arrayList);
            triggerInternal = triggerInternal(arrayList, triggerInternal);
        }
        if (triggerInternal == 0) {
            statScenarioTriggerFailed();
        }
    }

    public void triggerGuaranteeScenario() {
        Log.d("ScenarioTrigger", "Trigger guarantee scenarios");
        triggerInternal(this.mGuaranteeScenarios, 0);
    }
}
