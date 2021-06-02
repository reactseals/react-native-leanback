import React, { useRef, useImperativeHandle } from 'react';
import {
  requireNativeComponent,
  UIManager,
  findNodeHandle,
} from 'react-native';
import type { GridProperties } from './types';
import { REQUEST_FOCUS_ACTION } from './constants';

const LeanbackGrid6Col = requireNativeComponent('LeanbackGrid6Col');
const LeanbackGrid5Col = requireNativeComponent('LeanbackGrid5Col');
const LeanbackGrid4Col = requireNativeComponent('LeanbackGrid4Col');

const getGridView = (numOfCols?: number) => {
  switch (numOfCols) {
    case 6:
      return LeanbackGrid6Col;
    case 5:
      return LeanbackGrid5Col;
    default:
      return LeanbackGrid4Col;
  }
};

const Grid = React.forwardRef((opts: GridProperties, ref) => {
  const rowRef: React.RefObject<any> = useRef();

  const {
    key,
    style,
    data,
    forbiddenFocusDirections,
    attributes,
    nextFocusUpId,
    nextFocusDownId,
    nextFocusLeftId,
    nextFocusRightId,
    showOnlyFocusedInfo,
    numOfCols,
    onFocus,
    onPress,
    onDataIdsReady,
  } = opts;

  const attrs = {
    data: data,
    attributes: {
      width: attributes?.width || 513,
      height: attributes?.height || 176,
      forbiddenFocusDirections:
        forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections)
          ? forbiddenFocusDirections
          : [],
      nextFocusUpId: nextFocusUpId || -1,
      nextFocusDownId: nextFocusDownId || -1,
      nextFocusLeftId: nextFocusLeftId || -1,
      nextFocusRightId: nextFocusRightId || -1,
      cardShape: attributes?.cardShape || 'square',
      borderRadius: 0,
      showOnlyFocusedInfo: showOnlyFocusedInfo ?? false,
      hasImageOnly: attributes.hasImageOnly ?? false,
      imageTransformationMode:
        attributes?.imageTransformationMode || 'fitCenter',
    },
  };

  useImperativeHandle(ref, () => ({
    requestFocus: () => {
      requestFocus();
    },
  }));

  const requestFocus = () => {
    const node = findNodeHandle(rowRef.current);
    //@ts-ignore
    UIManager.dispatchViewManagerCommand(node, REQUEST_FOCUS_ACTION, []);
  };

  const GridView = getGridView(numOfCols);

  return (
    <GridView
      key={key}
      ref={rowRef}
      // @ts-ignore
      style={style}
      dataAndAttributes={attrs}
      onFocus={(event: any) => {
        const { item } = event.nativeEvent;
        if (onFocus) onFocus(JSON.parse(item));
      }}
      onPress={(event: any) => {
        const { item } = event.nativeEvent;
        if (onPress) onPress(JSON.parse(item));
      }}
      onDataIdsReady={(event: any) => {
        const { data: stringifiedData } = event.nativeEvent;
        if (onDataIdsReady) {
          const data = JSON.parse(stringifiedData);
          if (data.length) onDataIdsReady(data);
        }
      }}
    />
  );
});

export default Grid;
