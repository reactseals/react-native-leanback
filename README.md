# react-native-leanback
Android TV leanback wrapper for React Native

## Implementation

Move [styles.xml][link1] and [values.xml][link2] to your androidtv resources folder.

**[Renative][link3] implementation**

Add this code snipped to your `renative.json`

```
"@reactseals/react-native-leanback": {
    "version": "1.0.4",
    "androidtv": {
        "path": "packages/react-native-leanback/android",
        "package": "com.rs.leanbacknative.LeanbackNativePackage"
    }
}
```

**React native implementation implementation**

Install via `package.json`:

```
"@reactseals/react-native-leanback": "1.0.4"
```

Install from the command line:

```
npm install @reactseals/react-native-leanback@1.0.4
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

| Prop                        | Type        | Description                                               |
| --------------------------- | ----------- | --------------------------------------------------------- |
| ``data``                    | ``array``   | Data for row                                              |
| ``style``                   | ``object``  | Container holder style                                    |
| ``title``                   | ``string``  | Row title                                                 |
| ``attributes.width``        | ``integer`` | Width of row                                              |
| ``attributes.height``       | ``integer`` | Height of row                                             |
| ``attributes.hasImageOnly`` | ``boolean`` | Bool flag which indicates wheter show title or image only |

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

<p align="center">
    <img src="./misc/img/row1.gif" alt="React native leanback row with titles" />
</p>
<p align="center">
    <img src="./misc/img/row2.gif" alt="React native leanback row with no titles" />
</p>
<p align="center">
    <img src="./misc/img/row3.gif" alt="React native leanback row custom sized" />
</p>
<p align="center">
    <img src="./misc/img/row4.gif" alt="React native leanback row" />
</p>

## Grid

| Prop                        | Type        | Description                                               |
| --------------------------- | ----------- | --------------------------------------------------------- |
| ``data``                    | ``array``   | Data for row                                              |
| ``style``                   | ``object``  | Container holder style                                    |
| ``title``                   | ``string``  | Row title                                                 |
| ``attributes.width``        | ``integer`` | Width of row                                              |
| ``attributes.height``       | ``integer`` | Height of row                                             |
| ``attributes.hasImageOnly`` | ``boolean`` | Bool flag which indicates wheter show title or image only |

```javascript
import { Grid } from 'react-native-leanback';

<Grid
    data={data}
    attributes={{
        width: 313,
        height: 173,
        hasImageOnly: true,
    }}
    style={{ width: '100%' }}
    onFocus={(item) => console.log(item)}
    onPress={(item) => console.log(item)}
/>
```

<p align="center">
    <img src="./misc/img/grid1.gif" alt="React native leanback grid with titles" />
</p>
<p align="center">
    <img src="./misc/img/grid2.gif" alt="React native leanback grid with no titles" />
</p>

[link1]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/styles.xml
[link2]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/values.xml
[link3]: https://github.com/pavjacko/renative
