package com.example.etransportandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.etransportandroid.MainActivity
import com.example.etransportandroid.R
import com.example.etransportandroid.data.CommercialOrder
import com.example.etransportandroid.data.Database
import com.example.etransportandroid.data.PrivateOrder
import com.google.firebase.auth.FirebaseAuth

class PrivateBookingFragment: Fragment() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_private_booking, container, false)

        mAuth = FirebaseAuth.getInstance()

        val button = inflate.findViewById<Button>(R.id.submit_button)
        button.setOnClickListener {
            Database().writeNewPrivateOrder(mAuth.currentUser?.uid.toString(), PrivateOrder(
                    itemDescription = inflate.findViewById<EditText>(R.id.item_description_edittext).text.toString(),
                    weight = inflate.findViewById<EditText>(R.id.weight_edittext).text.toString(),
                    PickUpDate = inflate.findViewById<EditText>(R.id.pickup_date_edittext).text.toString(),
                    hazards = inflate.findViewById<EditText>(R.id.hazards_edittext).text.toString(),
                    truckId= inflate.findViewById<EditText>(R.id.truck_edittext).text.toString(),
                    bookingDate = inflate.findViewById<EditText>(R.id.pickup_date_edittext).text.toString(),
                    dimensions = PrivateOrder.Dimensions (
                            height = inflate.findViewById<EditText>(R.id.height_edittext).text.toString(),
                            length = inflate.findViewById<EditText>(R.id.lenght_edittext).text.toString(),
                            depth = inflate.findViewById<EditText>(R.id.depth_edittext).text.toString()
                    ),
                    locations = PrivateOrder.Locations(
                            to = inflate.findViewById<EditText>(R.id.location_to_edittext).text.toString(),
                            from = inflate.findViewById<EditText>(R.id.location_from_textview).text.toString()
                    )
            ))

            Toast.makeText(context, "Form submitted", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).addFragmentToActivity(HomeFragment())
        }
        return inflate
    }

}