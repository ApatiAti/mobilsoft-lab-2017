package com.example.mobsoft.webkorhaz.test;

import android.content.Context;
import android.util.Log;

import com.example.mobsoft.webkorhaz.BuildConfig;
import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.TestComponent;
import com.example.mobsoft.webkorhaz.mock.NetworkMockMemoryRepository;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.mobsoft.webkorhaz.TestHelper.setTestInjector;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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
public class MainPresenterTest {

    public static final String TAG = "MainPresenterTest";
    public static final String errorMessagePrefix = "Hiba történt a foglalási időpontok lekérdezésekor.";

    @Inject
    Repository memoryRepository;

    private MainPresenter mainPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainPresenter = new MainPresenter();
        ((TestComponent) MobSoftApplication.injector).inject(this);
        ((MemoryRepository) memoryRepository).clear();

    }

    @Test
    public void refreshAppointmentsFromServer_NoAppointmentIdDb(){
        Log.d(TAG, "refreshAppointment_NoAppointmentIdDb: start");

        User user = NetworkMockMemoryRepository.getUserOne();

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreshAppointmentsFromServer(user);


        verifyRefreshAppointmentFromsServerCall(mainScreen);


        Log.d(TAG, "refreshAppointment_NoAppointmentIdDb: finish");
    }

    @Test
    public void refreshAppointmentsFromServer_WithAppointmentInDb() {
        Log.d(TAG, "refreshAppointment_WithAppointmentInDb: start");

        User user = NetworkMockMemoryRepository.getUserOne();
        long remainingAppointmentId = 1000L;
        long updatedAppointmentId = 0L;
        String room = "nem szoba";

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();


        memoryRepository.saveAppointment(new Appointment(remainingAppointmentId, null, null ,null ,null ,null ,null, new User("xxxxxx", ""), null ,null));
        memoryRepository.saveAppointment(new Appointment(updatedAppointmentId, null, null , room,null ,null ,null, new User(1L, "beteg1", ""), null ,null));


        mainPresenter.refreshAppointmentsFromServer(user);


        verifyRefreshAppointmentFromsServerCall(mainScreen);
        List<Appointment> appointments;


        // Adatbázisban megmarad a másik Appointment
        appointments = MemoryRepository.appointmentList;
        assertEquals(2, appointments.size());
        for (Appointment appointment : appointments) {
            if (!appointment.getAppointmentId().equals(updatedAppointmentId)){
                assertNull(appointment.getConsultationHourType());
                assertNull(appointment.getDepartment());
                assertNotNull(appointment.getPatient());
                assertNotSame(room, appointment.getRoom());
            }
        }

        Log.d(TAG, "refreshAppointment_WithAppointmentInDb: finish");
    }

    @Test
    public void refreshAppointmentsFromServer_departmentError(){
        Log.d(TAG, "refreshAppointmentsFromServer_departmentError: start");

        User user = NetworkMockMemoryRepository.getUserOne();

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
//        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreshAppointmentsFromServer(user);


        verifyErrorMessage(mainScreen,errorMessagePrefix, 1);


        Log.d(TAG, "refreshAppointmentsFromServer_departmentError: finish");
    }

    @Test
    public void refreshAppointmentsFromServer_ConsultationHourError() {
        Log.d(TAG, "refreshAppointmentsFromServer_ConsultationHourError: start");

        User user = NetworkMockMemoryRepository.getUserOne();

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

//        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreshAppointmentsFromServer(user);


        verifyErrorMessage(mainScreen,errorMessagePrefix, 1);


        Log.d(TAG, "refreshAppointmentsFromServer_ConsultationHourError: finish");
    }

    @Test
    public void refreshAppointmentsFromServer_PatientError(){
        Log.d(TAG, "refreshAppointmentsFromServer_PatientError: start");

        User user = NetworkMockMemoryRepository.getUserOne();

        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
//        ((MemoryRepository) memoryRepository).createUser();

        assertTrue(memoryRepository.getAppointments(user).isEmpty());


        mainPresenter.refreshAppointmentsFromServer(user);


        verifyErrorMessage(mainScreen,errorMessagePrefix, 1);


        Log.d(TAG, "refreshAppointmentsFromServer_PatientError: finish");
    }

    @Test
    public void departmentAndTypesCall(){
        Log.d(TAG, "departmentAndTypesCall: started");
        callRefreshDepartment();


        List<Department> departments = memoryRepository.getDepartments();
        assertEquals(2l, departments.size());
        assertEquals(2l, departments.get(0).getConsultationHourTypeList().size());
        assertEquals(2l, departments.get(1).getConsultationHourTypeList().size());

        Log.d(TAG, "departmentAndTypesCall: finished ");
    }

    @Test
    public void departmentAndTypesCallWithDb(){
        Log.d(TAG, "departmentAndTypesCallWithDb: started");

        String updatedDepartmentName = "Ortopédiablag";
        Department alreadyInDbDepartment = new Department(100L, updatedDepartmentName, new ArrayList<ConsultationHourType>());
        String newDbDepartmentName = "Urológia";
        Department newDbDepartment = new Department(1000000L, newDbDepartmentName, new ArrayList<ConsultationHourType>());

        memoryRepository.saveDepartment(alreadyInDbDepartment);
        memoryRepository.saveDepartment(newDbDepartment);

        callRefreshDepartment();


        List<Department> departments = memoryRepository.getDepartments();
        assertEquals(3l, departments.size());

        for (Department dbDepartment : departments){
            Long departmentId = dbDepartment.getDepartmentId();

            if (departmentId.equals(alreadyInDbDepartment.getDepartmentId())){
                assertFalse(updatedDepartmentName.equals(dbDepartment.getDepartmentName()));
                assertFalse(alreadyInDbDepartment.getConsultationHourTypeList().isEmpty());

            } else if (departmentId.equals(newDbDepartment.getDepartmentId())){
                assertTrue(newDbDepartmentName.equals(dbDepartment.getDepartmentName()));
                assertFalse(alreadyInDbDepartment.getConsultationHourTypeList().isEmpty());
            }
        }

        Log.d(TAG, "departmentAndTypesCallWithDb: finished");
    }

    private void callRefreshDepartment() {
        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);

        mainPresenter.refreshDepartmentData();

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(mainScreen, times(1)).showMessage(messageCaptor.capture());

        List<String> messages = messageCaptor.getAllValues();
        assertEquals("Sikeres adatbázis frissítés", messages.get(0));
    }



    @After
    public void tearDown() {
        mainPresenter.detachScreen();
        Log.d(TAG, "");
        Log.d(TAG, "");
    }

    private void verifyRefreshAppointmentFromsServerCall(MainScreen mainScreen) {
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(mainScreen, times(1)).showAppointments(captor.capture());

        List<Appointment> appointments = captor.getAllValues().get(0);
        assertEquals(1, appointments.size());
        Appointment newAppointment = appointments.get(0);
        assertNotNull(newAppointment.getConsultationHourType());
        assertNotNull(newAppointment.getDepartment());
        assertNotNull(newAppointment.getPatient());
    }

    private void verifyErrorMessage(MainScreen mainScreen, String expectedMessage, int times) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mainScreen, times(times)).showMessage(captor.capture());

        String errorMessage = captor.getAllValues().get(0);
        assertTrue(errorMessage.startsWith(expectedMessage));
    }

}
