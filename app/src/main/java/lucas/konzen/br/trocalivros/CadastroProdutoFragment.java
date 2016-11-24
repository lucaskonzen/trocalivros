package lucas.konzen.br.trocalivros;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;




public class CadastroProdutoFragment extends Fragment {



private ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_cadastro_produto,null);



        imageButton = (ImageButton) view.findViewById(R.id.imageButton);

        
        return (view);


    }
    /**
     * This method will be invoked when the user clicks a button
     *
     Este método será invocado quando o usuário clicar em um botão
     * @param v
     */


   /*
    public void onImageGalleryClicked(View v) {
        // invoke the image gallery using an implict intent.
        // invoca a galeria de imagens usando uma intenção implícita.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        // onde queremos encontrar os dados?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        // finalmente, obter uma representação URI
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        // definir os dados e tipo. Obter todos os tipos de imagem.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        // vamos invocar esta atividade, e obter algo de volta dela.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // if we are here, everything processed successfully.
            // se estamos aqui, tudo processado com sucesso.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.
                // Se estamos aqui, estamos ouvindo de volta da galeria de imagens.

                // the address of the image on the SD Card.
                // o endereço da imagem no cartão SD.
                Uri imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                // declarar um fluxo para ler os dados de imagem do cartão SD.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                // estamos recebendo um fluxo de entrada, baseado no URI da imagem.
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    // obter um bitmap do fluxo.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    // show the image to the user
                    // mostrar a imagem ao usuário
                    imageButton.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    // mostra uma mensagem ao usuário indiciando que a imagem não está disponível.
                    Toast.makeText(getActivity(), "Não foi possível abrir a imagem", Toast.LENGTH_LONG).show();
                }

            }
        }
    }*/
}
