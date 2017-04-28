package com.example.mobsoft.webkorhaz.test;

import android.util.Log;

import com.example.mobsoft.webkorhaz.BuildConfig;
import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.TestComponent;
import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
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
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Apati on 2017.04.28..
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DepartmentTest {

    public static final String TAG = "DepartmentTest";

    @Inject
    Repository memoryRepository;

    private MainPresenter mainPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        ((TestComponent)MobSoftApplication.injector).inject(this);
        mainPresenter = new MainPresenter();
        ((MemoryRepository) memoryRepository).clear();

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
        assertEquals("Sikeres department frissítés", messages.get(0));
    }

    @After
    public void tearDown() {
        mainPresenter.detachScreen();
        Log.d(TAG, "");
        Log.d(TAG, "");
    }
}
