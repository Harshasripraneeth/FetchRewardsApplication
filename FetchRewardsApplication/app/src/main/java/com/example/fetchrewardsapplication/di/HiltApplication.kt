package com.example.fetchrewardsapplication.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/***
 *  Dagger-Hilt needs an application class annotated with HiltAndroidApp.
 */
@HiltAndroidApp
class HiltApplication: Application()