package com.pruebas.abmp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.pruebas.abmp.dataApi.ServiceABMP;
import com.pruebas.abmp.models.Animal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnimalsList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnimalsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalsList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String AMPHIBIANS = "Amphibia";
    private static final String BIRDS = "aves";
    private static final String MAMMALS = "Mammalia";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Retrofit retrofit;
    private static final String BASEURL = "https://www.datos.gov.co/resource/";
    private static final String TAG = "ANIMALS";

    private RecyclerView recyclerView;
    private int offset;
    private String orderParam;
    private boolean enableLoad;
    AnimalListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public AnimalsList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalsList.
     */
    // TODO: Rename and change types and number of parameters
    public AnimalsList(String param1,String param2) {
        this.mParam1=param1;
        this.mParam2=param2;
    }

    public static AnimalsList newInstance(String param1, String param2) {
        AnimalsList fragment = new AnimalsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            //--------------
            offset=0;
            orderParam=":id";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_animals_list, container, false);
        //-------------------------
        Spinner order = v.findViewById(R.id.spOrder);
        final ArrayList<String> itemOrder = new ArrayList<>();
        itemOrder.add(getString(R.string.io_none));
        itemOrder.add(getString(R.string.io_order));
        itemOrder.add(getString(R.string.io_family));
        itemOrder.add(getString(R.string.io_gender));
        itemOrder.add(getString(R.string.io_dangerRate));

        order.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,itemOrder));


        recyclerView = v.findViewById(R.id.rvListA);
        adapter = new AnimalListAdapter();
        adapter.setOrderParam(orderParam);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = manager.getChildCount();
                    int totalItemContent = manager.getItemCount();
                    int pasVisibleItems = manager.findFirstVisibleItemPosition();

                    if(enableLoad){
                        if(visibleItemCount + pasVisibleItems >= totalItemContent){
                            enableLoad = false;
                            offset+=20;
                            getData(offset, orderParam); // CARGADOR
                        }
                    }
                }
            }
        });

        order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(itemOrder.get(i).equals(getString(R.string.io_none)))    orderParam = ":id";
                if(itemOrder.get(i).equals(getString(R.string.io_order)))    orderParam = "orden";
                if(itemOrder.get(i).equals(getString(R.string.io_family)))    orderParam = "familia";
                if(itemOrder.get(i).equals(getString(R.string.io_gender)))    orderParam = "g_nero";
                if(itemOrder.get(i).equals(getString(R.string.io_dangerRate)))    orderParam = "estado_de_amenaza";

                offset=0;
                adapter = new AnimalListAdapter();
                adapter.setOrderParam(orderParam);
                recyclerView.setAdapter(adapter);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Animal> as = adapter.getAnimals();
                        ((MainActivity)getActivity()).showViewAnimal(as.get(recyclerView.getChildAdapterPosition(view)));
                    }
                });
                recyclerView.setHasFixedSize(true);
                final LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(manager);
                getData(offset, orderParam);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        enableLoad = true;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData(offset, orderParam); //CARGADOR
        //-------------------------
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void getData(int offset, String orderParam){
        Call<ArrayList<Animal>> openResponseCall = null;
        ServiceABMP service = retrofit.create(ServiceABMP.class);
        if(mParam1.equals(AMPHIBIANS)){
            openResponseCall = service.getListAmphibians("distinct*",orderParam,20,offset);
        }
        if(mParam1.equals(BIRDS)){
            openResponseCall = service.getListBirds("distinct*",orderParam,20,offset);
        }
        if(mParam1.equals(MAMMALS)){
            openResponseCall = service.getListMammals("distinct*",orderParam,20,offset);
        }

        openResponseCall.enqueue(new Callback<ArrayList<Animal>>() {
            @Override
            public void onResponse(Call<ArrayList<Animal>> call, Response<ArrayList<Animal>> response) {

                enableLoad = true;
                if(response.isSuccessful()){
                    ArrayList<Animal> ans = response.body();
                    adapter.addList(ans);

                }else{
                    Log.e(TAG,"onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Animal>> call, Throwable t) {

            }
        });

    }
}
