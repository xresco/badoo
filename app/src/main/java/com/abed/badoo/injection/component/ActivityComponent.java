package com.abed.badoo.injection.component;

import com.abed.badoo.ui.Main.MainActivity;
import com.abed.badoo.injection.PerActivity;
import com.abed.badoo.injection.module.ActivityModule;
import com.abed.badoo.ui.Transactions.TransactionsActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(TransactionsActivity transactionsActivity);

}
