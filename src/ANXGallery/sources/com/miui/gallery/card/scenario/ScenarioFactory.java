package com.miui.gallery.card.scenario;

import android.util.SparseArray;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.miui.gallery.card.scenario.time.annual.AnnualEventsScenario;
import com.miui.gallery.card.scenario.time.annual.AnnualFoodsScenario;
import com.miui.gallery.card.scenario.time.annual.AnnualPetsScenario;
import com.miui.gallery.card.scenario.time.annual.AnnualTravelsScenario;
import com.miui.gallery.card.scenario.time.cloud.CloudTimeScenario;
import com.miui.gallery.card.scenario.time.free.CatMonthlyScenario;
import com.miui.gallery.card.scenario.time.free.DogMonthlyScenario;
import com.miui.gallery.card.scenario.time.free.FoodMonthlyScenario;
import com.miui.gallery.card.scenario.time.free.PastTravelScenario;
import com.miui.gallery.card.scenario.time.free.PastWeekendScenario;
import com.miui.gallery.card.scenario.time.guarantee.RecentMemoryScenario;
import com.miui.gallery.card.scenario.time.pastyear.ChineseHolidayScenario;
import com.miui.gallery.card.scenario.time.pastyear.PastYearActivities;
import com.miui.gallery.card.scenario.time.pastyear.PastYearMonthScenario;
import com.miui.gallery.card.scenario.time.pastyear.PastYearParty;
import com.miui.gallery.card.scenario.time.pastyear.PastYearSeasonScenario;
import com.miui.gallery.card.scenario.time.pastyear.PastYearSports;
import com.miui.gallery.card.scenario.time.pastyear.SolarHolidayScenario;
import com.miui.gallery.card.scenario.time.recent.LastMonthScenario;
import com.miui.gallery.card.scenario.time.recent.LastSeasonScenario;
import com.miui.gallery.card.scenario.time.recent.LastWeekendScenario;
import com.miui.gallery.card.scenario.time.recent.RecentTravelScenario;
import com.miui.gallery.card.scenario.time.recent.YesterdayActivitiesScenario;
import com.miui.gallery.card.scenario.time.recent.YesterdayPartyScenario;
import com.miui.gallery.card.scenario.time.recent.YesterdaySportsScenario;
import com.miui.gallery.cloudcontrol.strategies.AssistantScenarioStrategy;
import com.miui.gallery.util.Log;

public class ScenarioFactory {
    private static final SparseArray<Class<? extends Scenario>> sScenarioClass = new SparseArray<>();

    static {
        sScenarioClass.put(118, AnnualTravelsScenario.class);
        sScenarioClass.put(119, AnnualEventsScenario.class);
        sScenarioClass.put(120, AnnualPetsScenario.class);
        sScenarioClass.put(BaiduSceneResult.STATION, AnnualFoodsScenario.class);
        sScenarioClass.put(109, LastMonthScenario.class);
        sScenarioClass.put(108, LastSeasonScenario.class);
        sScenarioClass.put(110, LastWeekendScenario.class);
        sScenarioClass.put(114, RecentTravelScenario.class);
        sScenarioClass.put(116, YesterdayActivitiesScenario.class);
        sScenarioClass.put(115, YesterdayPartyScenario.class);
        sScenarioClass.put(117, YesterdaySportsScenario.class);
        sScenarioClass.put(101, ChineseHolidayScenario.class);
        sScenarioClass.put(112, PastYearActivities.class);
        sScenarioClass.put(103, PastYearMonthScenario.class);
        sScenarioClass.put(111, PastYearParty.class);
        sScenarioClass.put(104, PastYearSeasonScenario.class);
        sScenarioClass.put(113, PastYearSports.class);
        sScenarioClass.put(102, SolarHolidayScenario.class);
        sScenarioClass.put(401, CatMonthlyScenario.class);
        sScenarioClass.put(402, DogMonthlyScenario.class);
        sScenarioClass.put(403, FoodMonthlyScenario.class);
        sScenarioClass.put(201, PastTravelScenario.class);
        sScenarioClass.put(404, PastWeekendScenario.class);
        sScenarioClass.put(501, RecentMemoryScenario.class);
    }

    public static Scenario createCloudTimeScenario(AssistantScenarioStrategy.CloudTimeScenarioRule cloudTimeScenarioRule) {
        if (cloudTimeScenarioRule == null) {
            return null;
        }
        CloudTimeScenario cloudTimeScenario = new CloudTimeScenario(cloudTimeScenarioRule.getScenarioId());
        cloudTimeScenario.onFillScenarioRule(cloudTimeScenarioRule);
        return cloudTimeScenario;
    }

    public static Scenario createLocalScenario(AssistantScenarioStrategy.ScenarioRule scenarioRule) {
        Class cls;
        if (scenarioRule == null || (cls = sScenarioClass.get(scenarioRule.getScenarioId())) == null) {
            return null;
        }
        try {
            Scenario scenario = (Scenario) cls.newInstance();
            scenario.onFillScenarioRule(scenarioRule);
            return scenario;
        } catch (InstantiationException e) {
            Log.w("ScenarioFactory", (Throwable) e);
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e2) {
            Log.w("ScenarioFactory", (Throwable) e2);
            throw new IllegalStateException(e2);
        }
    }
}
