package com.example.agenda;

import android.provider.BaseColumns;

public class ContactContract {

        public static abstract class ContactEntry implements BaseColumns {
            public static final String TABLE_NAME ="contact";
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String SURNAME = "surname";
            public static final String NICKNAME = "nickname";
            public static final String PHONE_NUMBER = "phoneNumber";
            public static final String AVATAR_URI = "avatarUri";
            public static final String BIRTHDAY = "birthday";

    }
}
