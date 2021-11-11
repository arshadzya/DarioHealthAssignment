package com.arshad.dariohealthassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arshad.dariohealthassignment.viewmodel.MainActivityViewModel
import junit.framework.TestCase
import org.junit.Rule

class MainActivityViewModelTest : TestCase() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    public override fun setUp() {
        super.setUp()

    }

    public override fun tearDown() {}
}