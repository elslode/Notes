package slode.elsloude.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.resyclerViewNotes);
        if (notes.isEmpty()) {
            notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
            notes.add(new Note("Спортзал", "Упражнение 1", "Вторник", 2));
            notes.add(new Note("Машина", "Поменять масло", "Четверг", 3));
            notes.add(new Note("Работа", "Задание от Босса", "Понедельник", 1));
            notes.add(new Note("Рука", "Снять гипс", "среда", 2));
            notes.add(new Note("Нога", "Снять гипс", "среда", 2));
            notes.add(new Note("Нос", "Снять гипс", "среда", 3));
            notes.add(new Note("Базука", "Снять гипс", "среда", 1));
        }
        final NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.onNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "clicked" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongCkick(int position) {
                notes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
    }
    private void remove(int position) {
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(MainActivity.this, Add_Note_Activity.class);
        startActivity(intent);
    }
}
