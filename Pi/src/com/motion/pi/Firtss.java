package com.motion.pi;
///*
// * Copyright 2014 Hieu Rocker
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package pi.motion.com.pi;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//
//import pi.motion.com.pi.library.EmojiconEditText;
//import pi.motion.com.pi.library.EmojiconGridFragment;
//import pi.motion.com.pi.library.EmojiconTextView;
//import pi.motion.com.pi.library.EmojiconsFragment;
//import pi.motion.com.pi.library.emoji.Emojicon;
//
//public class Firtss extends FragmentActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener {
//    EmojiconEditText mEditEmojicon;
//    EmojiconTextView mTxtEmojicon;
//    CheckBox mCheckBox;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.firtsss);
//        mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
////        mTxtEmojicon = (EmojiconTextView) findViewById(R.id.txtEmojicon).setVisibility(View.GONE);
//        mEditEmojicon.addTextChangedListener(new TextWatcherAdapter() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                mTxtEmojicon.setText(s);
//            }
//        });
////        mCheckBox = (CheckBox) findViewById(R.id.use_system_default);
////        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                mEditEmojicon.setUseSystemDefault(b);
////                mTxtEmojicon.setUseSystemDefault(b);
////                setEmojiconFragment(b);
////            }
////        });
//
//        setEmojiconFragment(false);
//        final Button go = (Button)findViewById(R.id.button57);
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(Firtss.this,Related.class);
//                startActivity(i);
//
//            }
//        });
//    }
//
//    private void setEmojiconFragment(boolean useSystemDefault) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault))
//                .commit();
//    }
//
//    @Override
//    public void onEmojiconClicked(Emojicon emojicon) {
//        EmojiconsFragment.input(mEditEmojicon, emojicon);
//    }
//
//    @Override
//    public void onEmojiconBackspaceClicked(View v) {
//        EmojiconsFragment.backspace(mEditEmojicon);
//    }
//}
