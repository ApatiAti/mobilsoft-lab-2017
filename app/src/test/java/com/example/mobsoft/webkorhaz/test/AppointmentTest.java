package com.example.mobsoft.webkorhaz.test;

import com.example.mobsoft.webkorhaz.BuildConfig;
import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static com.example.mobsoft.webkorhaz.TestHelper.setTestInjector;

/**
 * Created by Apati on 2017.04.28..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AppointmentTest {

    @Inject
    Repository memoryRepository;

    private MainPresenter mainPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainPresenter = new MainPresenter();

    }

    @Test
    public void succesfullDepartmentAndTypesCall(){

    }

    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }
}
