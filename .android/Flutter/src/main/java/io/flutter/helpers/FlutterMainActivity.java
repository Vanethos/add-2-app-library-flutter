package io.flutter.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;


public class FlutterMainActivity extends FlutterActivity implements FlutterFragment.FlutterEngineProvider {
    private static final String CHANNEL = "vanethos.com/experiments";
    public static final String CENAS = "cenas";

    // This is the method that others will use to create
    // an Intent that launches MyFlutterActivity.
    public static Intent createDefaultIntent(@NonNull Context launchingContext) {
        return new IntentBuilder().build(launchingContext);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GeneratedPluginRegistrant.registerWith(FlutterAddToApp.getInstance().getDataEngine().getPluginRegistry());

        new MethodChannel(FlutterAddToApp.getInstance().getDataEngine().getDartExecutor(), CHANNEL).setMethodCallHandler(
                (call, result) -> {
                    if (call.method.equals("exitActivity")) {
                        Intent intent = new Intent();
                        intent.putExtra(CENAS, "cenas");
                        this.setResult(RESULT_OK, intent);
                        this.finish();
                    } else {
                        result.notImplemented();
                    }
                });
    }

    // You need to define an IntentBuilder subclass so that the
    // IntentBuilder uses MyFlutterActivity instead of a regular FlutterActivity.
    private static class IntentBuilder extends FlutterActivity.IntentBuilder {
        // Override the constructor to specify your class.
        IntentBuilder() {
            super(FlutterMainActivity.class);
        }
    }

    // This is the method where you provide your existing FlutterEngine instance.
    @Nullable
    @Override
    public FlutterEngine getFlutterEngine(@NonNull Context context) {
        return FlutterAddToApp.getInstance().getDataEngine();
    }
}

