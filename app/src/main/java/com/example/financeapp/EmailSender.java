package com.example.financeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

public class EmailSender {

    public static void sendEmailWithAttachment(Context context, File jsonFile) {
        if (jsonFile == null || !jsonFile.exists()) {
            return;
        }

        // Получение URI через FileProvider
        Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", jsonFile);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/json");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@example.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Финансовый отчет");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "В приложении находится экспортированный отчет.");
        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Разрешение на чтение файла

        context.startActivity(Intent.createChooser(emailIntent, "Отправить email..."));
    }
}