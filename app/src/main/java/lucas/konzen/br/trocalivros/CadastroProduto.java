package lucas.konzen.br.trocalivros;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CadastroProduto extends Fragment {


    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_cadastro_produto,container,false);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }
}
