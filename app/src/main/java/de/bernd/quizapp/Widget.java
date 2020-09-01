package de.bernd.quizapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class Widget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        System.out.println("Code ist in updateAppWidget");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        //UserController.getInstance().getClock(views);
        //String time = UserController.getInstance().getClock(views);
        UserController.getInstance().getClock(views);
        //views.setTextViewText(R.id.textTime, UserController.getInstance().getClock(views));


        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        //views.setTextViewText(R.id.imageView, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            /*Intent updateWidget = new Intent(context, Widget.class); // Widget.class is your widget class
            updateWidget.setAction("update_widget");
            PendingIntent pending = PendingIntent.getBroadcast(context, 0, updateWidget, PendingIntent.FLAG_CANCEL_CURRENT);
            try {
                pending.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /*@Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Code ist in onReceive");
        if (intent.getAction().equals("update_widget")) {
            // Manual or automatic widget update started

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget);

            // Update text, images, whatever - here
            remoteViews.setTextViewText(R.id.textTime, "My updated text");

            // Trigger widget layout update
            AppWidgetManager.getInstance(context).updateAppWidget(
                    new ComponentName(context, Widget.class), remoteViews);
        }
    }*/

}

