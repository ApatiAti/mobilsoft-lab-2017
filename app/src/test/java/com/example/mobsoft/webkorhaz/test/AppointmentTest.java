package com.example.mobsoft.webkorhaz.test;

import android.support.annotation.NonNull;

import com.example.mobsoft.webkorhaz.BuildConfig;
import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.TestComponent;
import com.example.mobsoft.webkorhaz.mock.NetworkMockMemoryRepository;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.User;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.main.MainPresenter;
import com.example.mobsoft.webkorhaz.ui.main.MainScreen;
import com.example.mobsoft.webkorhaz.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import static com.example.mobsoft.webkorhaz.TestHelper.setTestInjector;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
        ((TestComponent) MobSoftApplication.injector).inject(this);
        mainPresenter = new MainPresenter();
        ((MemoryRepository) memoryRepository).clear();

    }

    @Test
    public void refreshAppointment_EmtpyDb(){
        User user = callRefreshAppointment();

        List<Appointment> appointments = memoryRepository.getAppointments(user);

        assertEquals(2, appointments.size());
        for (Appointment appointment : appointments){
            assertNotNull(appointment.getConsultationHourType());
            assertNotNull(appointment.getDepartment());
            assertNotNull(appointment.getPatient());
        }
    }

    @Test
    public void refreshAppointment_WithDb() {
        String room = "nem szoba";

        memoryRepository.saveAppointment(new Appointment(1000L, null, null ,null ,null ,null ,null, new User("xxxxxx", ""), null ,null));
        memoryRepository.saveAppointment(new Appointment(0L, null, null , room,null ,null ,null, new User("beteg1", ""), null ,null));

        User user = callRefreshAppointment();

        List<Appointment> appointments = memoryRepository.getAppointments(user);

        assertEquals(2, appointments.size());
        for (Appointment appointment : appointments){
            assertNotNull(appointment.getConsultationHourType());
            assertNotNull(appointment.getDepartment());
            assertNotNull(appointment.getPatient());
            assertNotSame(room, appointment.getRoom());
        }
    }

    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }

    @NonNull
    private User callRefreshAppointment() {
        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);
        User user = NetworkMockMemoryRepository.getUseOne();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreashAppointments(user.getId());

        verify(mainScreen, times(1));
        return user;
    }
}
