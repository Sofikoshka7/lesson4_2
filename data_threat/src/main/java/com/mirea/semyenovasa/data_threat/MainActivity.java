package com.mirea.semyenovasa.data_threat;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.semyenovasa.data_threat.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText(
                "Различие методов выполнения Runnable в UI-потоке:\n" +
                        "- runOnUiThread(Runnable): используется в Activity. Немедленно ставит задачу в очередь главного потока.\n" +
                        "- post(Runnable): используется у View. Ставит задачу в очередь Looper главного потока. Может выполняться чуть позже runOnUiThread.\n" +
                        "- postDelayed(Runnable, long): откладывает выполнение задачи на заданное время (в миллисекундах).\n\n" +
                        "Последовательность выполнения методов:\n"
        );


        new Thread(() -> {
            // 1. View.post (немедленно)
            textView.post(() -> appendText("1. post"));

            // 2. View.postDelayed (через 500 мс)
            textView.postDelayed(() -> appendText("2. postDelayed (500ms)"), 500);

            // 3. Activity.runOnUiThread (немедленно)
            runOnUiThread(() -> appendText("3. runOnUiThread"));
        }).start();
    }

    private void appendText(String text) {
        runOnUiThread(() -> textView.append(text + "\n"));
    }
}