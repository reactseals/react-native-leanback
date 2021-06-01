import React, { useRef, useImperativeHandle } from 'react';
import {
  requireNativeComponent,
  UIManager,
  findNodeHandle,
} from 'react-native';
import type { RowProperties, CardData } from './types';
import { REQUEST_FOCUS_ACTION } from './constants';
const LeanbackNativeRow = requireNativeComponent('LeanbackRow');

const isValidTimestamp = (timestamp?: string | number): boolean => {
  if (timestamp && typeof timestamp === 'string') {
    return timestamp.length === 13;
  }

  return timestamp ? false : true;
};

const Row = React.forwardRef((opts: RowProperties, ref) => {
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
    onFocus,
    onPress,
    onDataIdsReady,
  } = opts;

  // Validate timestamps
  data.forEach((item: CardData) => {
    if (isValidTimestamp(item.programStartTimestamp))
      throw new Error(
        'Timestamp is of incorrect format. Must meet JS standart - miliseconds!'
      );

    if (isValidTimestamp(item.programEndTimestamp))
      throw new Error(
        'Timestamp is of incorrect format. Must meet JS standart - miliseconds!'
      );
  });

  const attrs = {
    data,
    attributes: {
      width: attributes?.width || 513,
      height: attributes?.height || 176,
      forbiddenFocusDirections:
        forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections)
          ? forbiddenFocusDirections
          : [],
      focusedCardAlignment: attributes?.focusedCardAlignment || 'left',
      numberOfRows: attributes?.numberOfRows || 1,
      nextFocusUpId: nextFocusUpId || -1,
      nextFocusDownId: nextFocusDownId || -1,
      nextFocusLeftId: nextFocusLeftId || -1,
      nextFocusRightId: nextFocusRightId || -1,
      cardShape: attributes?.cardShape || 'square',
      borderRadius: attributes?.borderRadius || 0,
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

  return (
    <LeanbackNativeRow
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

export default Row;
