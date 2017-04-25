package com.example.mobsoft.webkorhaz.interactor.todo;



import android.util.Log;

import com.example.mobsoft.webkorhaz.MobSoftApplication;
import com.example.mobsoft.webkorhaz.interactor.todo.events.GetFavouritesEvent;
import com.example.mobsoft.webkorhaz.interactor.todo.events.RemoveFavouriteEvent;
import com.example.mobsoft.webkorhaz.interactor.todo.events.SaveFavouriteEvent;
import com.example.mobsoft.webkorhaz.model.Todo;
import com.example.mobsoft.webkorhaz.network.todo.TodoApi;
import com.example.mobsoft.webkorhaz.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class FavouritesInteractor {

    @Inject
    Repository repository;
    @Inject
    TodoApi todoApi;
    
    @Inject
    EventBus bus;

    public FavouritesInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getFavourites() {
        GetFavouritesEvent event = new GetFavouritesEvent();
        try {
            List<Todo> todos = repository.getFavourites();
            event.setTodos(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveFavourites(Todo todos) {

        SaveFavouriteEvent event = new SaveFavouriteEvent();
        event.setTodo(todos);
        try {
            repository.saveFavourite(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateFavourites(List<Todo> todo) {
        try {
            repository.updateFavourites(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFavourites(Todo todos) {
        RemoveFavouriteEvent event = new RemoveFavouriteEvent();
        event.setTodos(todos);
        try {
            repository.removeFavourite(todos);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void testNetwork() {
        GetFavouritesEvent event = new GetFavouritesEvent();
        try {
            Call<ResponseBody> result = todoApi.getVanENetHu("www.vanenet.hu");
            Response<ResponseBody> httpPage = result.execute();
            List<Todo> todos = new ArrayList<>();
            Todo todo = new Todo();

            todo.setName(httpPage.body().string());
            Log.e("API", httpPage.body().string());

            todos.add(todo);
            event.setTodos(todos);

            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
