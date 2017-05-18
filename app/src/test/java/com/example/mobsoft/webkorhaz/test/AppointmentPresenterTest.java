package com.example.mobsoft.webkorhaz.test;

import android.util.Log;

import com.example.mobsoft.webkorhaz.BuildConfig;
import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.TestComponent;
import com.example.mobsoft.webkorhaz.interactor.appointment.events.AppointmentsEventCode;
import com.example.mobsoft.webkorhaz.model.Appointment;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.repository.MemoryRepository;
import com.example.mobsoft.webkorhaz.repository.Repository;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentActivity;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentPresenter;
import com.example.mobsoft.webkorhaz.ui.appointment.AppointmentScreen;
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
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Apati on 2017.04.28..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AppointmentPresenterTest {

    public static final String TAG = "ApointmentPresenterTest";

    @Inject
    Repository memoryRepository;

    private AppointmentPresenter appointmentPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        appointmentPresenter = new AppointmentPresenter();
        ((TestComponent)MobSoftApplication.injector).inject(this);
        ((MemoryRepository) memoryRepository).clear();
    }

    private void setupUpFullDB(){
        ((MemoryRepository) memoryRepository).createConsultationHourTypes();
        ((MemoryRepository) memoryRepository).createDepartments();
        ((MemoryRepository) memoryRepository).createUser();
    }

    @Test
    public void saveorUpdateAppointment_SaveSuccess(){
        Log.d(TAG, "saveorUpdateAppointment_success: started");
        MemoryRepository memoryRepository = (MemoryRepository) this.memoryRepository;

        setupUpFullDB();
        Appointment appointment = memoryRepository.getAppointmentOne();
        appointment.setId(null);

        Integer resultStringId = callSaveAppointment(appointment);
        assertTrue(AppointmentsEventCode.SAVE.getMessageStringId() == resultStringId);

        Appointment dbAppointment = memoryRepository.getAppointmentByAppointmentId(appointment.getAppointmentId(), appointment.getPatient().getId());
        assertTrue(appointment.equals(dbAppointment));

        Log.d(TAG, "saveorUpdateAppointment_success: finished ");
    }

    @Test
    public void saveorUpdateAppointment_UpdateSuccess(){
        Log.d(TAG, "saveorUpdateAppointment_success: started");

        String newComplaints = "Valami más";
        MemoryRepository memoryRepository = (MemoryRepository) this.memoryRepository;

        setupUpFullDB();
        memoryRepository.createAppointment();
        Appointment oldAppointment = memoryRepository.getAppointmentOne();
        Appointment appointment = memoryRepository.getAppointmentOne();
        appointment.setComplaints(newComplaints);

        Integer resultStringId = callSaveAppointment(appointment);
        assertTrue(AppointmentsEventCode.UPDATE.getMessageStringId() == resultStringId);

        Appointment dbAppointment = memoryRepository.getAppointmentByAppointmentId(appointment.getAppointmentId(), appointment.getPatient().getId());
        assertFalse(oldAppointment.getComplaints().equals(dbAppointment.getComplaints()));
        assertTrue(appointment.equals(dbAppointment));

        Log.d(TAG, "saveorUpdateAppointment_success: finished ");
    }

    private Integer callSaveAppointment(Appointment appointment) {
        AppointmentScreen appointmentScreen = mock(AppointmentActivity.class);
        appointmentPresenter.attachScreen(appointmentScreen);

        appointmentPresenter.saveorUpdateAppointment(appointment);

        ArgumentCaptor<Integer> messageCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(appointmentScreen, times(1)).navigateToAppointmentList(messageCaptor.capture());

        return messageCaptor.getValue();
    }

    @After
    public void tearDown() {
        appointmentPresenter.detachScreen();
        Log.d(TAG, "");
        Log.d(TAG, "");
    }
}
