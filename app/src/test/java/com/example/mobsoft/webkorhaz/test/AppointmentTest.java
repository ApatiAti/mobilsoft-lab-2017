package com.example.mobsoft.webkorhaz.test;

import android.util.Log;

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
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import static com.example.mobsoft.webkorhaz.TestHelper.setTestInjector;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;
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

    public static final String TAG = "AppointmentTest";

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
    public void refreshAppointment_NoAppointmentIdDb(){
        Log.d(TAG, "refreshAppointment_NoAppointmentIdDb: start");


        User user = NetworkMockMemoryRepository.getUseOne();
        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreashAppointments(user);


        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(mainScreen, times(1)).showAppointments(captor.capture());


        List<Appointment> appointments = captor.getAllValues().get(0);

        assertEquals(1, appointments.size());
        Appointment appointment = appointments.get(0);
        assertNotNull(appointment.getConsultationHourType());
        assertNotNull(appointment.getDepartment());
        assertNotNull(appointment.getPatient());


        Log.d(TAG, "refreshAppointment_NoAppointmentIdDb: finish");
    }

    @Test
    public void refreshAppointment_WithAppointmentInDb() {
        Log.d(TAG, "refreshAppointment_WithAppointmentInDb: start");

        User user = NetworkMockMemoryRepository.getUseOne();
        long remainingAppointmentId = 1000L;
        long updatedAppointmentId = 0L;
        String room = "nem szoba";

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);


        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();


        memoryRepository.saveAppointment(new Appointment(remainingAppointmentId, null, null ,null ,null ,null ,null, new User("xxxxxx", ""), null ,null));
        memoryRepository.saveAppointment(new Appointment(updatedAppointmentId, null, null , room,null ,null ,null, new User(1L, 1L, "beteg1", ""), null ,null));


        mainPresenter.refreashAppointments(user);


        verify(mainScreen, times(1)).showAppointments(new ArgumentCaptor<List<Appointment>>().capture());


        List<Appointment> appointments = MemoryRepository.appointmentList;

        assertEquals(2, appointments.size());
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(updatedAppointmentId)){
                assertNotNull(appointment.getConsultationHourType());
                assertNotNull(appointment.getDepartment());
                assertNotNull(appointment.getPatient());
                assertNotSame(room, appointment.getRoom());
            } else {
                assertNull(appointment.getConsultationHourType());
                assertNull(appointment.getDepartment());
                assertNotNull(appointment.getPatient());
                assertNotSame(room, appointment.getRoom());
            }
        }

        Log.d(TAG, "refreshAppointment_WithAppointmentInDb: finish");
    }


    @After
    public void tearDown() {
        mainPresenter.detachScreen();
        Log.d(TAG, "");
        Log.d(TAG, "");
    }


}
