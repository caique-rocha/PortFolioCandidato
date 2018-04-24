package com.java.malcoln.candidato;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.java.malcoln.candidato.activity_Candidato.CandidatoActivity;

import com.java.malcoln.candidato.activity_Candidato.EventosListagem;
import com.java.malcoln.candidato.activity_Candidato.JinglesListagem;
import com.java.malcoln.candidato.activity_Candidato.PropostasListagem;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    JSONObject response, profile_pic_data, profile_pic_url;
    TextView user_name, user_email;
    ImageView user_picture;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button web = (Button) findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/candidato.messenger")));
            }
        });

        Button button = (Button) findViewById(R.id.button);
        Button imageShare = (Button) findViewById(R.id.imageShare);



        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");

        shareDialog = new ShareDialog(this);  // intialize facebook shareDialog.


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Candidato Messenger")
                            .setImageUrl(Uri.parse(
                            "https://www.facebook.com/candidato.messenger/photos/a.1309685332393573.1073741825.1309679042394202/1309685342393572/?type=1&theater"))
                            .setContentDescription("APLICATIVO CANDIDATO MESSENGER")
                            .setContentUrl(Uri.parse(
                            "https://www.facebook.com/candidato.messenger"))

                            .build();

                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });

        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    // this method is for create a dialog box to choose options to select Image to share on facebook.
    private void selectImage() {
        final CharSequence[] items = { "Tire uma Foto", "Escolha da sua Galeria",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Select profile Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Tire uma Foto")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Escolha da sua Galeria")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);
        }
    }

    /**** this method used for select image From Gallery  *****/


    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap thumbnail;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        thumbnail = BitmapFactory.decodeFile(selectedImagePath, options);

        ShareDialog(thumbnail);
    }

    /***  this method used for take profile photo *******/

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ShareDialog(thumbnail);
    }


    // This method is used to share Image on facebook timeline.
    public void ShareDialog(Bitmap imagePath){

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imagePath)
                .setCaption("Testing")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        shareDialog.show(content);

    }


    // Initialize the facebook sdk and then callback manager will handle the login responses.

    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
    }




    /*
       Set User Profile Information in Navigation Bar.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case R.id.nav_intro:
                startActivity(new Intent(getBaseContext(), MainActivity.class ));
                return true;

            case R.id.nav_mapa:
                startActivity(new Intent(getBaseContext(), CandidatoActivity.class ));
                return true;
            case R.id.nav_onibus:
                startActivity(new Intent(getBaseContext(), PropostasListagem.class));
                return true;
            case R.id.nav_empregos:
                startActivity(new Intent(getBaseContext(), WebActivity.class));
                return true;
            case R.id.nav_manage:
                startActivity(new Intent(getBaseContext(), EventosListagem.class));
                return true;
            case R.id.nav_chat:
                startActivity(new Intent(getBaseContext(), Main2Activity.class));
                return true;
            case R.id.nav_send:
                startActivity(new Intent(getBaseContext(), JinglesListagem.class));
                return true;
            case R.id.nav_face:
                startActivity(new Intent(getBaseContext(), MainActivityFaceB.class));
                return true;
            case R.id.nav_video:
                startActivity(new Intent(getBaseContext(), YouTubeActivity.class));
                return true;
            case R.id.nav_msg:
                startActivity(new Intent(getBaseContext(), FormContato.class));
                return true;
            case android.R.id.home:
                finish();

        case android.R.id.accessibilityActionScrollRight:
        finish();
    }
        return super.onOptionsItemSelected(item);
    }







    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
