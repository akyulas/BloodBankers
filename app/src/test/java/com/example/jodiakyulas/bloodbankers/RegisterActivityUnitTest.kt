package com.example.jodiakyulas.bloodbankers

import com.example.jodiakyulas.bloodbankers.activities.RegisterActivity
import com.example.jodiakyulas.bloodbankers.constants.Constants
import org.junit.Assert.*
import org.junit.Test

class RegisterActivityUnitTest {
    @Test
    fun testMatricCardNo() {
        assertEquals(true, RegisterActivity().matchMatricCardNo("U1621448G"))
        assertEquals(true, RegisterActivity().matchMatricCardNo("G1621448G"))
        assertEquals(false, RegisterActivity().matchMatricCardNo("X1234567G"))
        assertEquals(false, RegisterActivity().matchMatricCardNo("123456789"))
    }

    @Test
    fun testEmail() {
        assertEquals(true, RegisterActivity().matchEmail("THATOEOO001@e.ntu.edu.sg"))
        assertEquals(false,RegisterActivity().matchEmail("bolshack_fire@hotmail.com"))
    }

    @Test
    fun testPassword() {
        assertEquals(false, RegisterActivity().matchPassword("123"))
        assertEquals(false, RegisterActivity().matchPassword("12345678"))
        assertEquals(false, RegisterActivity().matchPassword("1234567A"))
        assertEquals(true, RegisterActivity().matchPassword("a1234567A"))
    }

//    @Test
//    fun canCreateBloodBankDataset() {
//        assert(AppointmentActivity().createBloodBankDataset().get(0) is BloodBank)
//    }
//
//    @Test
//    fun checkIfBloodBankClassCreatedProperly() {
//        assertEquals("Bloodbank@HSA", AppointmentActivity().createBloodBankDataset().get(0).location)
//    }







}