package com.example.lucascarvalho.adopet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalView extends AppCompatActivity {
    Button btnVoltar;
    TextView passedName = null;
    TextView passedPorte = null;
    TextView passedIdade = null;
    TextView passedDono = null;
    TextView passedSexo = null;
    ImageView passedImg1;
    ImageView passedImg2;
    ImageView passedImg3;
    ImageView passedImg4;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_view);

        btnVoltar=(Button)findViewById(R.id.btnVoltar);
        i=getIntent().getIntExtra("idd",0);

        passedName=(TextView)findViewById(R.id.edtNome);
        passedName.setText(MainActivity.sqLiteHelper.selectData(i).getNome());

        passedPorte=(TextView)findViewById(R.id.edtPorte);
        passedPorte.setText(MainActivity.sqLiteHelper.selectData(i).getPorte());

        passedIdade=(TextView)findViewById(R.id.edtIdade);
        passedIdade.setText(Integer.toString(MainActivity.sqLiteHelper.selectData(i).getIdade()));

        passedDono=(TextView)findViewById(R.id.edtDono);
        passedDono.setText(MainActivity.sqLiteHelper.selectData(i).getDono());

        passedSexo=(TextView)findViewById(R.id.edtSexo);
        passedSexo.setText(MainActivity.sqLiteHelper.selectData(i).getSexo());

        passedImg1 = (ImageView)findViewById(R.id.imageViewAnimalSolo1);
        byte[] animalImage = MainActivity.sqLiteHelper.selectData(i).getImage1();
        Bitmap bitmap = BitmapFactory.decodeByteArray(animalImage, 0, animalImage.length);
        passedImg1.setImageBitmap(bitmap);

        passedImg2 = (ImageView)findViewById(R.id.imageViewAnimalSolo2);
        byte[] animalImage2 = MainActivity.sqLiteHelper.selectData(i).getImage2();
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(animalImage2, 0, animalImage2.length);
        passedImg2.setImageBitmap(bitmap2);

        passedImg3 = (ImageView)findViewById(R.id.imageViewAnimalSolo3);
        byte[] animalImage3 = MainActivity.sqLiteHelper.selectData(i).getImage3();
        Bitmap bitmap3 = BitmapFactory.decodeByteArray(animalImage3, 0, animalImage3.length);
        passedImg3.setImageBitmap(bitmap3);

        passedImg4 = (ImageView)findViewById(R.id.imageViewAnimalSolo4);
        byte[] animalImage4 = MainActivity.sqLiteHelper.selectData(i).getImage4();
        Bitmap bitmap4 = BitmapFactory.decodeByteArray(animalImage4, 0, animalImage4.length);
        passedImg4.setImageBitmap(bitmap4);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AnimalView.this, AnimalList.class);
                startActivity(i);
            }
        });

    }


}
