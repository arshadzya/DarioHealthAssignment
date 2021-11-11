package com.arshad.dariohealthassignment.data

class TimeUtility {

       fun getCurrentTimeInMilliSecond():Long =  System.currentTimeMillis()

       fun getDifferenceTimeInMilliSecond(oldTime: Long) : Long = System.currentTimeMillis() - oldTime
}