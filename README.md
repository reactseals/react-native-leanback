# react-native-leanback
Android TV leanback wrapper for React Native

<p align="center">
    <img src="./misc/img/grid.gif" alt="React native leanback row with titles" />
</p>
<p align="center">
    <img src="./misc/img/row.gif" alt="React native leanback row custom sized" />
</p>

## Installation

Move [styles.xml][link1] and [values.xml][link2] to your androidtv resources folder.

Install via `package.json`:

```
"@reactseals/react-native-leanback": "2.0.2"
```

Install from the command line:

```
npm install @reactseals/react-native-leanback@2.0.2
```

## Usage

```javascript
import { Row } from '@reactseals/react-native-leanback';

<Row
    data={data}
    attributes={{
        width: 313,
        height: 173,
    }}
    style={{ width: '100%' }}
    title="Title for row"
    onFocus={(item) => console.log(item)}
    onPress={(item) => console.log(item)}
/>
```

```javascript
import { Grid } from 'react-native-leanback';

<Grid
    data={data}
    attributes={{
        width: 313,
        height: 173,
    }}
    style={{ width: '100%' }}
    onFocus={(item) => console.log(item)}
    onPress={(item) => console.log(item)}
    onDataIdsReady={(item) => console.log(item)}
/>
```


## Data Model

| Key                     | Required | Description                                                                                                                     |
| ----------------------- | -------- | ------------------------------------------------------------------------------------------------------------------------------- |
| `id`                    | `yes`    | Unique id of the card                                                                                                           |
| `cardImageUrl`          | `yes`    | Card image url                                                                                                                  |
| `videoUrl`              | `no`     | Video url                                                                                                                       |
| `title`                 | `no`     | Title of the card, visible underneath of the image                                                                              |
| `description`           | `no`     | Description   of the card, visible  underneath of the title                                                                     |
| `liveBadgeColor`        | `no`     | Color of the badge which represents live stream                                                                                 |
| `liveProgressBarColor`  | `no`     | Progress bar color                                                                                                              |
| `overlayImageUrl`       | `no`     | Url of overlay image visible on the top of the card image                                                                       |
| `overlayText`           | `no`     | Overlay text visible on the top of card image                                                                                   |
| `overlayPosition`       | `no`     | Position of overlay text                                                                                                        |
| `progress`              | `no`     | Percentage which indicates progress of live stream                                                                              |
| `backgroundColor`       | `no`     | Background color of the card                                                                                                    |
| `programStartTimestamp` | `no`     | Timestamp of live stream start. Must be combined with `programEndTimestamp` then progress gets automatically calculated         |
| `programEndTimestamp`   | `no`     | Timestamp of live stream end. Must be combined with `programStartTimestamp` then progress gets automatically calculated         |
| `viewId`                | `no`     | Unique viewId which can be used to prevent or force focus. If it's not defined then random unique id is generated automatically |


## Props

| Prop                              | Type                                           | Default  | Description                                                                                   |
| --------------------------------- | ---------------------------------------------- | -------- | --------------------------------------------------------------------------------------------- |
| `data`                            | `array`                                        |          | Data for row. See [Data Model](#data-model)                                                   |
| `style`                           | `object`                                       |          | Container holder style                                                                        |
| `title`                           | `string`                                       |          | Row title                                                                                     |
| `numOfCols`                       | `enum(4, 5, 6)`                                | `4`      | Number how many columns grid should contain(Grid only)                                        |
| `showOnlyFocusedInfo`             | `boolean`                                      | `false`  | Show info field block underneath ONLY when card is focused(Grid only)                         |
| `forbiddenFocusDirections`        | `array of enum('up', 'down', 'left', 'right')` |          | Prevents any element to be focused when user navigates out of grid/row to provided directions |
| `nextFocusUpId`                   | `string`                                       |          | Designates the next view to receive focus when the user navigates up                          |
| `nextFocusDownId`                 | `string`                                       |          | Designates the next view to receive focus when the user navigates down                        |
| `nextFocusLeftId`                 | `string`                                       |          | Designates the next view to receive focus when the user navigates left                        |
| `nextFocusRightId`                | `string`                                       |          | Designates the next view to receive focus when the user navigates right                       |
| `attributes.width`                | `integer`                                      |          | Width of card                                                                                 |
| `attributes.height`               | `integer`                                      |          | Height of card                                                                                |
| `attributes.cardShape`            | `enum('round', 'square')`                      | `square` | Shape of the card                                                                             |
| `attributes.focusedCardAlignment` | `enum('left', 'center')`                       | `center` | Alignment of focus                                                                            |
| `attributes.numberOfRows`         | `number`                                       | `1`      | Number of rows in Row component                                                               |
| `attributes.borderRadius`         | `number`                                       |          | Border radius                                                                                 |
|`attributes.imageTransformationMode`|`enum('noTransformation','centerCrop')`|`fitCenter`| Transformation mode of the card image |

## Methods

### `requestFocus()`

Imperative method for requesting focus of specific Row or Grid E.g.

```javascript
ref.current.requestFocus()
```

Here `ref.current` refers [to the `ref`](https://reactjs.org/docs/react-api.html#reactcreateref) passed to the `Grid` or `Row` component.

## Example

//TODO 

[link1]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/styles.xml
[link2]: https://github.com/reactseals/react-native-leanback/blob/master/android/src/main/res/values/values.xml
