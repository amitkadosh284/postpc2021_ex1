package android.exercise.mini.interactions;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  private boolean isEditing = false;
  private String currentTitle = "";
  // TODO:
  //  you can add fields to this class. those fields will be accessibly inside any method
  //  (like `onCreate()` and `onBackPressed()` methods)
  // for any field, make sure to set it's initial value. You CAN'T write a custom constructor
  // for example, you can add this field:
  // `private boolean isEditing = false;`
  // in onCreate() set `this.isEditing` to `true` once the user starts editing, set to `false` once done editing
  // in onBackPressed() check `if(this.isEditing)` to understand what to do

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.isEditing = true;
    this.currentTitle = "Page title here";
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {

//      TODO:
//      1. animate out the "start edit" FAB
        fabStartEdit.setVisibility(View.GONE);
//      2. animate in the "done edit" FAB
        fabEditDone.setVisibility(View.VISIBLE);
//      3. hide the static title (text-view)
        textViewTitle.setVisibility(View.GONE);
//      4. show the editable title (edit-text)
        editTextTitle.setVisibility(View.VISIBLE);
//      5. make sure the editable title's text is the same as the static one
        this.currentTitle = editTextTitle.getText().toString();
        editTextTitle.setText(textViewTitle.getText().toString());
//      6. optional (HARD!) make the keyboard to open with the edit-text focused,
//          so the user can start typing without the need another click on the edit-text
//
//      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
//       */
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      this.isEditing = false;

//      TODO:
//      1. animate out the "done edit" FAB
      fabEditDone.setVisibility(View.GONE);
//      2. animate in the "start edit" FAB
      fabStartEdit.setVisibility(View.VISIBLE);
//      3. take the text from the user's input in the edit-text and put it inside the static text-view
      textViewTitle.setText(this.currentTitle);
//      4. show the static title (text-view)
      textViewTitle.setVisibility(View.VISIBLE);
//      5. hide the editable title (edit-text)
      editTextTitle.setVisibility(View.GONE);
//      6. make sure that the keyboard is closed
      closeKeyBoard();
//
//      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready

    });
  }

  private void closeKeyBoard()
  {
    View view = getCurrentFocus();
    if (view != null)
    {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE );
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  @Override
  public void onBackPressed() {
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);
    // BACK button was clicked

//    TODO:
//    if user is now editing, tap on BACK will revert the edit. do the following:
    if (this.isEditing){
//    1. hide the edit-text
      editTextTitle.setVisibility(View.GONE);
//    2. show the static text-view with previous text (discard user's input)
      textViewTitle.setVisibility(View.VISIBLE);
//    3. animate out the "done-edit" FAB
      fabEditDone.setVisibility(View.GONE);
//    4. animate in the "start-edit" FAB
      fabStartEdit.setVisibility(View.VISIBLE);
    }

    else {
      super.onBackPressed();
    }
  }
}