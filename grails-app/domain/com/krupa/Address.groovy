package com.krupa

class Address {
 String street
 String city
 String state
 String zip
    static constraints = {
		street blank:false
		city null:true
		state blank:false
		zip range:5..9
    }
}
