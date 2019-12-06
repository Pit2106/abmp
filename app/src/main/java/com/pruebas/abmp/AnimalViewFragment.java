package com.pruebas.abmp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pruebas.abmp.models.Animal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnimalViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnimalViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Animal cAnimal;

    private OnFragmentInteractionListener mListener;

    public AnimalViewFragment() {
        // Required empty public constructor
    }

    public AnimalViewFragment(Animal a) {
        cAnimal=a;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimalViewFragment newInstance(String param1, String param2) {
        AnimalViewFragment fragment = new AnimalViewFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_animal_view, container, false);
        TextView tvNameC, tvKingdom, tvOrder, tvClase, tvFamily, tvGender, tvDanger;
        ImageView imgView;

        imgView=v.findViewById(R.id.imgView);

        tvNameC=v.findViewById(R.id.tvNameC);
        tvKingdom=v.findViewById(R.id.tvKingdom);
        tvClase=v.findViewById(R.id.tvClase);
        tvGender=v.findViewById(R.id.tvGender);
        tvOrder=v.findViewById(R.id.tvOrder);
        tvFamily=v.findViewById(R.id.tvFamily);
        tvDanger=v.findViewById(R.id.tvDanger);


        if(cAnimal.getKind().equals("Amphibia")) imgView.setImageResource(R.mipmap.ic_amphibians);
        if(cAnimal.getKind().equals("aves")) imgView.setImageResource(R.mipmap.ic_birds);
        if(cAnimal.getKind().equals("Mammalia")) imgView.setImageResource(R.mipmap.ic_mammals);

        tvNameC.append(cAnimal.getName());
        tvKingdom.append(cAnimal.getKingdom());
        tvClase.append(cAnimal.getKind());
        tvGender.append(cAnimal.getGender());
        tvOrder.append(cAnimal.getOrder());
        tvFamily.append(cAnimal.getFamily());

        if(cAnimal.getExt()==null) tvDanger.append(getString(R.string.dang_ne));
        else {
            if (cAnimal.getExt().equals("DD") || cAnimal.getExt().equals("Datos deficientes"))
                tvDanger.append(getString(R.string.dang_dd));
            if (cAnimal.getExt().equals("EX") || cAnimal.getExt().equals("Extinto"))
                tvDanger.append(getString(R.string.dang_ex));
            if (cAnimal.getExt().equals("EW") || cAnimal.getExt().equals("Extinto en Estado Silvestre"))
                tvDanger.append(getString(R.string.dang_ew));
            if (cAnimal.getExt().equals("CR") || cAnimal.getExt().equals("En Peligro Critico"))
                tvDanger.append(getString(R.string.dang_cr));
            if (cAnimal.getExt().equals("EN") || cAnimal.getExt().equals("En peligro"))
                tvDanger.append(getString(R.string.dang_en));
            if (cAnimal.getExt().equals("VU") || cAnimal.getExt().equals("Vulnerable"))
                tvDanger.append(getString(R.string.dang_vu));
            if (cAnimal.getExt().equals("NT") || cAnimal.getExt().equals("Casi amenazada"))
                tvDanger.append(getString(R.string.dang_nt));
            if (cAnimal.getExt().equals("LC") || cAnimal.getExt().equals("Preocupación menor"))
                tvDanger.append(getString(R.string.dang_lc));
        }

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
}
