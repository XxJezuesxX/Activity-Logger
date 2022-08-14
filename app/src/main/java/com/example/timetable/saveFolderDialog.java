package com.example.timetable;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class saveFolderDialog extends AppCompatDialogFragment {

    private saveFolderDialogListener listener;

    private EditText fileName;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.save_popup_window, null);

        dialogBuilder.setView(view)
                     .setTitle("")
                     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {

                         }
                     })
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String strFileName = fileName.getText().toString();
                            listener.assignFolderName(strFileName);
                        }
                    });


        fileName = view.findViewById(R.id.fileName);

        return dialogBuilder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (saveFolderDialogListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context +
                    "must implement the saveFolderDialogListener >:(");
        }
    }

    public interface saveFolderDialogListener{

        void assignFolderName(String fileName);

    }

}
