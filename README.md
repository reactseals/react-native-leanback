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

![React native leanback row with titles](https://media.giphy.com/media/NSAa5PLn8Y18TrnaIZ/source.gif)
![React native leanback row with no titles](https://media.giphy.com/media/6Bgo0R8v9v7QXm6uj1/source.gif)
![React native leanback row custom sized](https://media.giphy.com/media/KXwRQOjf10sTCU8J0w/source.gif)
![React native leanback row](https://media.giphy.com/media/EK26dv5pvEyASXFfbi/source.gif)

[link1]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/styles.xml
[link2]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/values.xml
[link3]: https://github.com/pavjacko/renative
