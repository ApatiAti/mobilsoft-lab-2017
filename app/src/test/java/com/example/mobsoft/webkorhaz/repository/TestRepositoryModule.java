package com.example.mobsoft.webkorhaz.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestRepositoryModule {

	@Singleton
	@Provides
	public Repository provideRepository() {
		return new MemoryRepository();
	}

	@Singleton
	@Provides
	public TestMemoryRepository provideEmptyMemoryRepository() {
		return new TestMemoryRepository();
	}
}