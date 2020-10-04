# react-native-leanback
Android TV leanback wrapper for React Native

## Implementation

Move [styles.xml][link1] and [values.xml][link2] to your androidtv resources folder.

**[Renative][link3] implementation**

Add this code snipped to your `renative.json`
```
"react-native-leanback": {
    "version": "github:reactseals/react-native-leanback#master",
    "androidtv": {
        "path": "packages/react-native-leanback/android",
        "package": "com.rs.leanbacknative.LeanbackNativePackage"
    }
}
```

## Data Model

```
{
    "id": "1",
    "cardImageUrl": "https://miro.medium.com/max/3376/1*0RTfTupYYCswBxAa2LOnog.png",
    "backdropUrl": "https://miro.medium.com/max/3376/1*0RTfTupYYCswBxAa2LOnog.png",
    "title": "Zeitgeist 2010_ Year in Review",
    "description": "Fusce id nisi turpis. Praesent viverra bibendum semper."
}
```

# Usage

## Row
<img src="./misc/img/row1.gif" alt="React native leanback row with titles" style="display: block; margin: 10px 0;" />
<img src="./misc/img/row2.gif" alt="React native leanback row with no titles" style="display: block; margin: 10px 0;" />
<img src="./misc/img/row3.gif" alt="React native leanback row custom sized" style="display: block; margin: 10px 0;" />
<img src="./misc/img/row4.gif" alt="React native leanback row" style="display: block; margin: 10px 0;" />

```javascript
import { Row } from 'react-native-leanback';

<Row
    data={data}
    attributes={{
        width: 313,
        height: 173,
        hasImageOnly: true,
    }}
    style={{ width: '100%' }}
    title="Title for row"
    onFocus={(item) => console.log(item)}
    onPress={(item) => console.log(item)}
/>
```

[link1]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/styles.xml
[link2]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/values.xml
[link3]: https://github.com/pavjacko/renative
