package com.app.network.helper

import android.app.Application
import android.content.Context

class MainApp:Application() {


    companion object{

        private lateinit var _context:Context
        private lateinit var _session:Session

        val context get() = _context
        val session get() = _session

        private fun setContext(context:Context){
            _context = context
        }

        private fun initSession(session: Session){
            _session = session
        }

    }

    override fun onCreate() {
        super.onCreate()

        setContext(this)

        initSession(Session.with(context)!!)


    }
}