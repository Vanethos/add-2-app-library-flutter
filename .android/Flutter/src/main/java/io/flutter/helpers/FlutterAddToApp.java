package io.flutter.helpers;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;
import io.reactivex.Completable;


public class FlutterAddToApp {
    private static FlutterAddToApp singleton;
    public static final int TEST = 47;

    private Application context;

    private FlutterEngine dataEngine;

    public FlutterEngine getDataEngine() {
        return dataEngine;
    }

    public static void initalize(Application context) {
        singleton = new FlutterAddToApp(context);
    }

    private FlutterAddToApp(Application context) {
        this.context = context;
        Completable.fromAction(() -> setupFlutter(context)).subscribe();
    }

    public void startFlutterActivity(Activity context) {
        Intent intent = FlutterMainActivity.createDefaultIntent(context);
        context.startActivityForResult(intent, TEST);
    }

    private void initializeDataOnlyEngine(Application appContext) {
        // Instantiate a FlutterEngine.
        dataEngine = new FlutterEngine(appContext);

        // Define a DartEntrypoint
        DartExecutor.DartEntrypoint entrypoint = new DartExecutor.DartEntrypoint(
                appContext.getAssets(),
                FlutterMain.findAppBundlePath(appContext),
                "main"
        );

        // Execute the DartEntrypoint within the FlutterEngine.
        dataEngine.getDartExecutor().executeDartEntrypoint(entrypoint);
    }

    private void setupFlutter(Application context) {
        FlutterMain.startInitialization(context);
        FlutterMain.ensureInitializationComplete(context, null);

        initializeDataOnlyEngine(context);
    }

    public static synchronized FlutterAddToApp getInstance() {
        if (singleton == null) throw new AssertionError("Must first initialize in Application");
        return singleton;
    }
}
