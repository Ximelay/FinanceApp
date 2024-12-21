package com.example.financeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class EmailSender {

    public static void sendEmailWithAttachment(Context context, File jsonFile) {
        if (jsonFile == null || !jsonFile.exists()) {
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/json");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@example.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Финансовый отчет");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "В приложении находится экспортированный отчет.");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(jsonFile));

        context.startActivity(Intent.createChooser(emailIntent, "Отправить email..."));
    }
}