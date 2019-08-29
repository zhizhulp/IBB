package com.example.ibb.ui.ui.mine.simple;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class SimpleTextWatcher implements TextWatcher {
	
	@Override
	public void afterTextChanged(Editable s) {
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}
	
	@Override
	public abstract void onTextChanged(CharSequence s, int start, int before, int count);
	
}
