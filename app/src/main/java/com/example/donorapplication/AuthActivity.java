package com.example.donorapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.donorapplication.Entity.Exercise;
import com.example.donorapplication.Entity.News;
import com.example.donorapplication.Entity.ThreadComment;
import com.example.donorapplication.Entity.ThreadPost;
import com.example.donorapplication.Entity.Topic;
import com.example.donorapplication.Entity.User;
import com.example.donorapplication.Entity.UserSchedule;
import com.example.donorapplication.Utils.AuthUtil;
import com.example.donorapplication.Utils.CustomHashing;

import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AuthActivity extends AppCompatActivity {

    AppDatabase db;

    public void setInitialData(){
        // Creating threadPosts
        db.threadPostDAO().insert(new ThreadPost (0, "How to bench press?", "I am trying to do a solid normal benchpress from like past year but still have not developed form. What am i doin wrong?", R.drawable.ellipse_10__3_, 1, 0, 0, 3, "02-05-2022"));
        db.threadPostDAO().insert(new ThreadPost (1, "Leg day is the worst day on earth", "I hate training legs. Change my mind.", R.drawable.ellipse_10__3_, 0, 1, 1, 1, "02-05-2022"));
        db.threadPostDAO().insert(new ThreadPost (2, "Sigma rule #231724.\n" +
                "Never trust women", "Men are superior gender on earth.", R.drawable.ellipse_10__3_, 1, 1, 2, 2, "02-05-2022"));
        db.threadPostDAO().insert(new ThreadPost (3, "Trying to leg press", "Tell me secrets that i should know before start.", R.drawable.ellipse_10__3_, 0, 0, 3, 0, "02-05-2022"));
        db.threadPostDAO().insert(new ThreadPost (4, "Creatine or turinabol?", "I want to be bik and stronk. I wont to it supliments and grou a lot of masl.", R.drawable.ellipse_10__3_, 1, 0, 4, 2, "02-05-2022"));
        db.threadPostDAO().insert(new ThreadPost (5, "I'm an ectomorph, please help...", "Is  your bodytype really matters?? Ot is it just a myth.", R.drawable.ellipse_10__3_, 0, 1, 5, 3, "02-05-2022"));

        // Creating threadComments
        db.threadCommentDAO().insert(new ThreadComment(0, 5, "Really nice question bro! All of us need to start somewhere. First of all, make sure that you are not doing too much weight. Second of all, just follow the right technique from internet. This should be enough :)", 0, "UPVOTED", "02-05-2022"));
        db.threadCommentDAO().insert(new ThreadComment(1, 4, "are you fucking dumb?", 1, "DOWNVOTED", "02-05-2022"));
        db.threadCommentDAO().insert(new ThreadComment(2, 3, "opinion rejected", 2, "DOWNVOTED", "02-05-2022"));
        db.threadCommentDAO().insert(new ThreadComment(3, 4, "Fully approved! Keep it up, king!", 2, "UPVOTED", "02-05-2022"));
        db.threadCommentDAO().insert(new ThreadComment(4, 1, "Man, just try to workout yourself. Being natty is not what you need.", 4, "UPVOTED", "02-05-2022"));
        db.threadCommentDAO().insert(new ThreadComment(5, 1, "another child crying that they are an ectomorphs and can't actually gain some mass.", 5, "DOWNVOTED", "02-05-2022"));

        // Creating exercises
        Drawable drawable = getResources().getDrawable(R.drawable.jump_rope);
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            final byte[] bitmapdata = stream.toByteArray();
            db.exerciseDAO().insert(new Exercise(0, "Jump rope", 4, 20, 1, bitmapdata));
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable.workout);
        if (drawable2 != null) {
            Bitmap bitmap2 = ((BitmapDrawable) drawable2).getBitmap();
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
            final byte[] bitmapdata2 = stream2.toByteArray();
            db.exerciseDAO().insert(new Exercise(1, "Workout", 4, 20, 1, bitmapdata2));
        }
        Drawable drawable3 = getResources().getDrawable(R.drawable.running);
        if (drawable3 != null) {
            Bitmap bitmap3 = ((BitmapDrawable) drawable3).getBitmap();
            ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
            bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
            final byte[] bitmapdata3 = stream3.toByteArray();
            db.exerciseDAO().insert(new Exercise(2, "Running", 4, 20, 1, bitmapdata3));
        }

        Drawable drawable4 = getResources().getDrawable(R.drawable.squad);
        if (drawable4 != null) {
            Bitmap bitmap4 = ((BitmapDrawable) drawable4).getBitmap();
            ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
            bitmap4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
            final byte[] bitmapdata4 = stream4.toByteArray();
            db.exerciseDAO().insert(new Exercise(3, "Squad", 4, 20, 1, bitmapdata4));
        }

        Drawable drawable5 = getResources().getDrawable(R.drawable.benchpress);
        if (drawable5 != null) {
            Bitmap bitmap5 = ((BitmapDrawable) drawable5).getBitmap();
            ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
            bitmap5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
            final byte[] bitmapdata5 = stream5.toByteArray();
            db.exerciseDAO().insert(new Exercise(4, "Bench press", 4, 30, 1, bitmapdata5));
        }

        Drawable drawable6 = getResources().getDrawable(R.drawable.boxing);
        if (drawable6 != null) {
            Bitmap bitmap6 = ((BitmapDrawable) drawable6).getBitmap();
            ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
            bitmap6.compress(Bitmap.CompressFormat.PNG, 100, stream6);
            final byte[] bitmapdata6 = stream6.toByteArray();
            db.exerciseDAO().insert(new Exercise(5, "Boxing", 1, 40, 1, bitmapdata6));
        }

        Drawable drawable7 = getResources().getDrawable(R.drawable.handstand);
        if (drawable7 != null) {
            Bitmap bitmap7 = ((BitmapDrawable) drawable7).getBitmap();
            ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
            bitmap7.compress(Bitmap.CompressFormat.PNG, 100, stream7);
            final byte[] bitmapdata7 = stream7.toByteArray();
            db.exerciseDAO().insert(new Exercise(6, "Handstand", 2, 10, 1, bitmapdata7));
        }

        Drawable drawable8 = getResources().getDrawable(R.drawable.yoga_pose);
        if (drawable8 != null) {
            Bitmap bitmap8 = ((BitmapDrawable) drawable8).getBitmap();
            ByteArrayOutputStream stream8 = new ByteArrayOutputStream();
            bitmap8.compress(Bitmap.CompressFormat.PNG, 100, stream8);
            final byte[] bitmapdata8 = stream8.toByteArray();
            db.exerciseDAO().insert(new Exercise(7, "Yoga", 1, 60, 1, bitmapdata8));
        }

        Drawable drawable9 = getResources().getDrawable(R.drawable.cycle);
        if (drawable9 != null) {
            Bitmap bitmap9 = ((BitmapDrawable) drawable9).getBitmap();
            ByteArrayOutputStream stream9 = new ByteArrayOutputStream();
            bitmap9.compress(Bitmap.CompressFormat.PNG, 100, stream9);
            final byte[] bitmapdata9 = stream9.toByteArray();
            db.exerciseDAO().insert(new Exercise(8, "Cycling", 1, 20, 1, bitmapdata9));
        }

        Drawable drawable10 = getResources().getDrawable(R.drawable.fitness);
        if (drawable10 != null) {
            Bitmap bitmap10 = ((BitmapDrawable) drawable10).getBitmap();
            ByteArrayOutputStream stream10 = new ByteArrayOutputStream();
            bitmap10.compress(Bitmap.CompressFormat.PNG, 100, stream10);
            final byte[] bitmapdata10 = stream10.toByteArray();
            db.exerciseDAO().insert(new Exercise(9, "Abs", 3, 25, 1, bitmapdata10));
        }

        Drawable drawable11 = getResources().getDrawable(R.drawable.grip);
        if (drawable11 != null) {
            Bitmap bitmap11 = ((BitmapDrawable) drawable11).getBitmap();
            ByteArrayOutputStream stream11 = new ByteArrayOutputStream();
            bitmap11.compress(Bitmap.CompressFormat.PNG, 100, stream11);
            final byte[] bitmapdata11 = stream11.toByteArray();
            db.exerciseDAO().insert(new Exercise(10, "Grip training", 2, 15, 1, bitmapdata11));
        }

        Drawable drawable12 = getResources().getDrawable(R.drawable.heart);
        if (drawable12 != null) {
            Bitmap bitmap12 = ((BitmapDrawable) drawable12).getBitmap();
            ByteArrayOutputStream stream12 = new ByteArrayOutputStream();
            bitmap12.compress(Bitmap.CompressFormat.PNG, 100, stream12);
            final byte[] bitmapdata12 = stream12.toByteArray();
            db.exerciseDAO().insert(new Exercise(11, "Cardio", 1, 40, 1, bitmapdata12));
        }

        Drawable drawable13 = getResources().getDrawable(R.drawable.burpees);
        if (drawable13 != null) {
            Bitmap bitmap13 = ((BitmapDrawable) drawable13).getBitmap();
            ByteArrayOutputStream stream13 = new ByteArrayOutputStream();
            bitmap13.compress(Bitmap.CompressFormat.PNG, 100, stream13);
            final byte[] bitmapdata13 = stream13.toByteArray();
            db.exerciseDAO().insert(new Exercise(12, "Burpees", 2, 20, 1, bitmapdata13));
        }

        Drawable drawable14 = getResources().getDrawable(R.drawable.meditation);
        if (drawable14 != null) {
            Bitmap bitmap14 = ((BitmapDrawable) drawable14).getBitmap();
            ByteArrayOutputStream stream14 = new ByteArrayOutputStream();
            bitmap14.compress(Bitmap.CompressFormat.PNG, 100, stream14);
            final byte[] bitmapdata14 = stream14.toByteArray();
            db.exerciseDAO().insert(new Exercise(13, "Meditation", 1, 60, 1, bitmapdata14));
        }

        // Creating news
        Drawable newsDrawable1 = getResources().getDrawable(R.drawable.bruce_lee);
        if (newsDrawable1 != null) {
            Bitmap newsBitmap1 = ((BitmapDrawable) newsDrawable1).getBitmap();
            ByteArrayOutputStream newsStream1 = new ByteArrayOutputStream();
            newsBitmap1.compress(Bitmap.CompressFormat.PNG, 100, newsStream1);
            final byte[] newsBitmapData1 = newsStream1.toByteArray();
            db.newsDAO().insert(new News(0, "Bruce Lee Death Anniversary", "07/20/2019", "news18.com", "Actor, director and martial artist Bruce Lee passed away on July 20, 1973, shortly before the release of his film Enter the Dragon, at the age of 32. The legendary actor and the founder of the hybrid martial arts Jeet Kune Do needs no introduction.\n" +
                    "\n" +
                    "Bruce Lee remains the greatest icon of martial arts cinema, but there are a lot of facts about the Enter The Dragon actor that remains relatively unknown. On his 46th death anniversary, here are some lesser-known facts about the actor and martial arts expert that will leave you amazed:", "https://www.news18.com/news/movies/bruce-lee-death-anniversary-here-are-some-lesser-known-facts-about-the-martial-arts-icon-2238301.html", newsBitmapData1));
        }

        Drawable newsDrawable2 = getResources().getDrawable(R.drawable.eddie);
        if (newsDrawable2 != null) {
            Bitmap newsBitmap2 = ((BitmapDrawable) newsDrawable2).getBitmap();
            ByteArrayOutputStream newsStream2 = new ByteArrayOutputStream();
            newsBitmap2.compress(Bitmap.CompressFormat.PNG, 100, newsStream2);
            final byte[] newsBitmapData2 = newsStream2.toByteArray();
            db.newsDAO().insert(new News(1, "Eddie Hall Agreed To Get The Mountain's Name Name Tattooed On Him If He Lost", "21/03/2022", "sportbible.com", "Eddie Hall could be heading to a tattoo parlour soon after he lost his boxing match to Hafþór Júlíus Björnsson.\n" +
                    "\n" +
                    "The two faced off at Dubai Duty Free Tennis Stadium in what was dubbed the 'world's heaviest' bout.\n" +
                    "\n" +
                    "While the two were impressive, it was the Game of Thrones actor nicknamed The Mountain who came out on top by unanimous decision after six rounds.", "https://www.sportbible.com/australia/boxing-eddie-hall-agreed-to-get-the-mountains-name-name-tattooed-on-him-20220321", newsBitmapData2));
        }

        Drawable newsDrawable3 = getResources().getDrawable(R.drawable.whey_protein);
        if (newsDrawable3 != null) {
            Bitmap newsBitmap3 = ((BitmapDrawable) newsDrawable3).getBitmap();
            ByteArrayOutputStream newsStream3 = new ByteArrayOutputStream();
            newsBitmap3.compress(Bitmap.CompressFormat.PNG, 100, newsStream3);
            final byte[] newsBitmapData3 = newsStream3.toByteArray();
            db.newsDAO().insert(new News(2, "The hidden dangers of protein powders", "10/04/2020", "health.harvard.edu", "Protein powders are powdered forms of protein that come from plants (soybeans, peas, rice, potatoes, or hemp), eggs, or milk (casein or whey protein). The powders may include other ingredients such as added sugars, artificial flavoring, thickeners, vitamins, and minerals. The amount of protein per scoop can vary from 10 to 30 grams. Supplements used for building muscle contain relatively more protein, and supplements used for weight loss contain relatively less.", "https://www.health.harvard.edu/staying-healthy/the-hidden-dangers-of-protein-powders", newsBitmapData3));
        }

        Drawable newsDrawable4 = getResources().getDrawable(R.drawable.tour_de_france);
        if (newsDrawable4 != null) {
            Bitmap newsBitmap4 = ((BitmapDrawable) newsDrawable4).getBitmap();
            ByteArrayOutputStream newsStream4 = new ByteArrayOutputStream();
            newsBitmap4.compress(Bitmap.CompressFormat.PNG, 100, newsStream4);
            final byte[] newsBitmapData4 = newsStream4.toByteArray();
            db.newsDAO().insert(new News(3, "Alaphilippe cleared to train indoors but Tour de France still in doubt", "12/05/2022", "cyclingnews.com", "Julian Alaphilippe has been cleared to start training on the rollers three weeks after he crashed out of Liège-Bastogne-Liège but his participation in this year’s Tour de France remains in doubt, just 50 days from the start in Copenhagen.", "https://www.cyclingnews.com/news/alaphilippe-cleared-to-train-indoors-but-tour-de-france-still-in-doubt/", newsBitmapData4));
        }

        Drawable newsDrawable5 = getResources().getDrawable(R.drawable.planet_fintess);
        if (newsDrawable5 != null) {
            Bitmap newsBitmap5 = ((BitmapDrawable) newsDrawable5).getBitmap();
            ByteArrayOutputStream newsStream5 = new ByteArrayOutputStream();
            newsBitmap5.compress(Bitmap.CompressFormat.PNG, 100, newsStream5);
            final byte[] newsBitmapData5 = newsStream5.toByteArray();
            db.newsDAO().insert(new News(4, "Planet Fitness Celebrates Grand Opening Of “Judgement Free” Gym In Schaumburg", "09/05/2022", "clubindustry.com", "Grand opening attendees will enjoy giveaways and raffle prizes that include sports tickets and souvenirs from the Chicago Bulls, Schaumburg Boomers, Chicago Wolves and the Northwestern Wildcats along with a special appearance from Wolves mascot – Skates the Gray Wolf. Additionally, the Chicago Bulls six championship trophies will be on display in club for guests to see and take pictures with during their visit. There will also be a DJ on hand with the chance to win plenty of Planet Fitness giveaways.", "https://www.clubindustry.com/press-releases/planet-fitness-celebrates-grand-opening-judgement-free-gym-schaumburg", newsBitmapData5));
        }

        // Creating topics
        db.topicDAO().insert(new Topic(0,  "Martial arts", R.drawable.photo_1550759808_37c78fb8f1e3));
        db.topicDAO().insert(new Topic(1,  "Nutrition", R.drawable.nutrition));
        db.topicDAO().insert(new Topic(2,  "Yoga", R.drawable.woman_performing_yoga_square_medium));
        db.topicDAO().insert(new Topic(3,  "Powerlifting", R.drawable.powerlifting_the_definitive_guide));

        // Creating users
        Drawable userDrawable1 = getResources().getDrawable(R.drawable.gigachad);
        if (userDrawable1 != null) {
            Bitmap userBitmap1 = ((BitmapDrawable) userDrawable1).getBitmap();
            ByteArrayOutputStream userStream1 = new ByteArrayOutputStream();
            userBitmap1.compress(Bitmap.CompressFormat.PNG, 100, userStream1);
            byte[] userAvatar1 = userStream1.toByteArray();
            db.userDao().register(new User(0, "Giga", "Chad", "gigachad@mail.ru", 165, 2, 25, "yes", 0, 24, 0, userAvatar1, null, String.valueOf(new Date())));
        }

        Drawable userDrawable2 = getResources().getDrawable(R.drawable.alex);
        if (userDrawable2 != null) {
            Bitmap userBitmap2 = ((BitmapDrawable) userDrawable2).getBitmap();
            ByteArrayOutputStream userStream2 = new ByteArrayOutputStream();
            userBitmap2.compress(Bitmap.CompressFormat.PNG, 100, userStream2);
            byte[] userAvatar2 = userStream2.toByteArray();
            db.userDao().register(new User(1, "Alex", "Lee", "youdoit@mail.ru", 70, 175, 20, "46452020", 20, 24, 80, userAvatar2, null, String.valueOf(new Date())));
        }

        Drawable userDrawable3 = getResources().getDrawable(R.drawable.yerzhanchik);
        if (userDrawable3 != null) {
            Bitmap userBitmap3 = ((BitmapDrawable) userDrawable3).getBitmap();
            ByteArrayOutputStream userStream3 = new ByteArrayOutputStream();
            userBitmap3.compress(Bitmap.CompressFormat.PNG, 100, userStream3);
            byte[] userAvatar3 = userStream3.toByteArray();
            db.userDao().register(new User(2, "Yerzhan", "Utegenov", "abobus@mail.ru", 5215, 22515, 20, "turinarium", 0, 24, 0, userAvatar3, null, String.valueOf(new Date())));
        }

        Drawable userDrawable4 = getResources().getDrawable(R.drawable.lyosha);
        if (userDrawable4 != null) {
            Bitmap userBitmap4 = ((BitmapDrawable) userDrawable4).getBitmap();
            ByteArrayOutputStream userStream4 = new ByteArrayOutputStream();
            userBitmap4.compress(Bitmap.CompressFormat.PNG, 100, userStream4);
            byte[] userAvatar4 = userStream4.toByteArray();
            db.userDao().register(new User(3, "Дядя", "Лёша", "alkash@mail.ru", 30, 24, 20, "vodka", 0, 24, 0, userAvatar4, null, String.valueOf(new Date())));
        }

        Drawable userDrawable5 = getResources().getDrawable(R.drawable.morat);
        if (userDrawable5 != null) {
            Bitmap userBitmap5 = ((BitmapDrawable) userDrawable5).getBitmap();
            ByteArrayOutputStream userStream5 = new ByteArrayOutputStream();
            userBitmap5.compress(Bitmap.CompressFormat.PNG, 100, userStream5);
            byte[] userAvatar5 = userStream5.toByteArray();
            db.userDao().register(new User(4, "dhliy", "chel", "deadInside@mail.ru", 75, 200, 22, "mrtvy", 12, 30, 200, userAvatar5, null, String.valueOf(new Date())));
        }

        Drawable userDrawable6 = getResources().getDrawable(R.drawable.peterparker);
        if (userDrawable6 != null) {
            Bitmap userBitmap6 = ((BitmapDrawable) userDrawable6).getBitmap();
            ByteArrayOutputStream userStream6 = new ByteArrayOutputStream();
            userBitmap6.compress(Bitmap.CompressFormat.PNG, 100, userStream6);
            byte[] userAvatar6 = userStream6.toByteArray();
            db.userDao().register(new User(5, "Peter", "Parker", "spiderman@mail.ru", 75, 200, 22, "webpautina", 12, 30, 200, userAvatar6, null, String.valueOf(new Date())));
        }

        db.userDao().register(new User(6, "User", "6", "6@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(7, "User", "7", "7@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(8, "User", "8", "8@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(9, "User", "9", "9@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(10, "User", "10", "10@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(11, "User", "11", "11@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
        db.userDao().register(new User(12, "User", "12", "12@mail.ru", 165, 2, 25, "yes", 0, 24, 0, null, null, String.valueOf(new Date())));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = AppDatabase.getInstance(this);
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("dbConnection", MODE_PRIVATE);
        if (!preferences.getString("generatedValue", "").equals("true")){
            setInitialData();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("generatedValue", "true");
            editor.apply();
        }
        setContentView(R.layout.activity_auth);
    }

    public void signIn(View view) throws NoSuchAlgorithmException {
        EditText loginField = (EditText) findViewById(R.id.loginInputToSignup);
        String login = loginField.getText().toString();
        EditText passwordField = (EditText) findViewById(R.id.passwordInputToSignup);
        String password = passwordField.getText().toString();
        User user = db.userDao().authenticate(login, password);
        if (user == null){
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(AuthActivity.this);
            builder.setMessage("Invalid data");
            builder.setTitle("Oops!");
            builder.setCancelable(false);
            builder
                    .setNegativeButton(
                            "OK",
                            new DialogInterface
                                    .OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else{
            SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            String hashedToken = CustomHashing.sha256(user.getEmail()+user.getPassword());
            editor.putString("token", "Bearer "+user.getId()+"|"+hashedToken);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void register(View view) {
        setContentView(R.layout.register);
    }

    public void login(View view) {
        setContentView(R.layout.login);
    }

    public void signUp(View view) throws SQLException {
        EditText emailField = (EditText) findViewById(R.id.editEmailToSignup);
        String login = emailField.getText().toString();
        User userData = db.userDao().findByLogin(login);
        if (userData == null) {
            EditText passwordField = (EditText) findViewById(R.id.editPasswordToSignup);
            String password = emailField.getText().toString();
            EditText passwordConfirmationField = (EditText) findViewById(R.id.editPasswordConfirmationToSignup);
            String passwordConfirmation = emailField.getText().toString();
            if (!password.equals(passwordConfirmation)) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(AuthActivity.this);
                builder.setMessage("Seems like your passwords are not the same");
                builder.setTitle("Oops!");
                builder.setCancelable(false);
                builder
                        .setNegativeButton(
                                "OK",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                EditText fnameField = (EditText) findViewById(R.id.editFirstNameToSignup);
                String fname = emailField.getText().toString();
                EditText lnameField = (EditText) findViewById(R.id.editSecondNameToSignup);
                String lname = emailField.getText().toString();
                EditText estimatedWeightField = (EditText) findViewById(R.id.editEstimatedWeightToSignup);
                String estimatedWeight = emailField.getText().toString();
                EditText freeTimeField = (EditText) findViewById(R.id.editFreeTimePerWeekSignup);
                String freeTime = emailField.getText().toString();
                EditText heightField = (EditText) findViewById(R.id.editHeightToSignup);
                String height = emailField.getText().toString();
                EditText ageField = (EditText) findViewById(R.id.editAgeToSignup);
                String age = emailField.getText().toString();
                EditText weightField = (EditText) findViewById(R.id.editWeightToSignup);
                String weight = emailField.getText().toString();
                EditText bodyFatField = (EditText) findViewById(R.id.editBodyFatToSignup);
                String bodyFat = emailField.getText().toString();
                int lastIndex = db.userDao().getAll().size();
                User user = new User(lastIndex, fname, lname, login, Integer.valueOf(weight), Integer.valueOf(height), Integer.valueOf(age), password, Integer.valueOf(bodyFat), Integer.valueOf(freeTime), Integer.valueOf(estimatedWeight), null, null, String.valueOf(new Date()));
                db.userDao().register(user);
                String exercisesIds = "0 1 2 3 4 5 6 7 8 9 10 11 12 13";
                for (int i = 0; i<7;i++){
                    db.userScheduleDAO().insert(new UserSchedule(db.userScheduleDAO().getAll().size(), i, exercisesIds, lastIndex));
                }
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(AuthActivity.this);
                builder.setMessage("You have successfully registered!");
                builder.setTitle("Success!");
                builder.setCancelable(false);
                builder
                        .setNegativeButton(
                                "OK",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        setContentView(R.layout.login);
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        } else {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(AuthActivity.this);
            builder.setMessage("Seems like this email already registered");
            builder.setTitle("Oops!");
            builder.setCancelable(false);
            builder
                    .setNegativeButton(
                            "OK",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void guestSignIn(View view) throws NoSuchAlgorithmException {
        Date currentTime = Calendar.getInstance().getTime();
        String guestHash = CustomHashing.sha256(currentTime.toString());
        int lastIndex = db.userDao().getAll().size();
        User user = new User(lastIndex, "Guest", "", guestHash, 0, 0, 0, guestHash, 0, 0, 0, null, null, String.valueOf(new Date()));
        db.userDao().register(user);
        String exercisesIds = "0 1 2 3 4 5 6 7 8 9 10 11 12 13";
        for (int i = 0; i<7;i++){
            db.userScheduleDAO().insert(new UserSchedule(db.userScheduleDAO().getAll().size(), i, exercisesIds, lastIndex));
        }
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String hashedToken = CustomHashing.sha256(user.getEmail()+user.getPassword());
        editor.putString("token", "Bearer "+user.getId()+"|"+hashedToken);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}