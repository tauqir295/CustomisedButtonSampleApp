# Mobile design library

This library is used to manage all the custom views.

### CustomizableGenericButton
This is a customised button that has configurable title, sub title, and icon 

### Integration
``` 
    xml
            
            <com.example.mobile.design.lib.CustomizableGenericButton
                android:id="@+id/buttonOne"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                app:buttonSubtitleText="@string/button_subtitle"
                app:buttonTitleText="@string/button_title"
                app:icon="@drawable/ic_settings"
                app:iconVisible="true"
                android:layout_marginTop="@dimen/dimen_16dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/userTv"
                app:subtitleVisible="true" />

            <com.example.mobile.design.lib.CustomizableGenericButton
                android:id="@+id/buttonTwo"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/dimen_16dp"
                app:buttonSubtitleText="@string/button_subtitle"
                app:buttonTitleText="@string/button_title"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/buttonOne"
                app:subtitleVisible="true" />

            <com.example.mobile.design.lib.CustomizableGenericButton
                android:id="@+id/buttonThree"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/dimen_16dp"
                app:buttonTitleText="@string/button_title"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/buttonTwo" />

```

```
    code
        
        findViewById<CustomizableGenericButton>(R.id.buttonId).apply {
            titleText = "Continue"
            subtitleText = "done via code"
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_settings)
            subtitleTvVisibility = true
        }

        findViewById<CustomizableGenericButton>(R.id.buttonId).apply {
            titleText = "This is an example for long title"
            subtitleText = "This is an example sub title"
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_settings)
            iconVisibility = true
            subtitleTvVisibility = true
        }

```
### Theme
This component responds to theme change. Change the attributes as coded in `Theme.MDL` and `Theme.Flavored`.
Create own theme with similar attributes and add it in [manifest](../app/src/main/AndroidManifest.xml) `android:theme="@style/ThemeName"` to see the effect.

### Colors and dimensions
It is generally recommended to use the colors and dimensions from design library