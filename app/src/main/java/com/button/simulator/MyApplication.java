package com.button.simulator;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MyApplication extends Application {

    public Preferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = new Preferences(this);

        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(getResources().getString(R.string.metrika));
        if (preferences.isFirstTime(true)) {
            configBuilder.handleFirstActivationAsUpdate(true);
            preferences.setFirstTime(false);
        }
        YandexMetricaConfig extendedConfig = configBuilder.build();
        YandexMetrica.activate(getApplicationContext(), extendedConfig);
        YandexMetrica.enableActivityAutoTracking(this);
    }
}
