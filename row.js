import React, { useEffect, useRef, useImperativeHandle } from "react";
import {
  requireNativeComponent,
  UIManager,
  findNodeHandle,
} from "react-native";

const LeanbackNativeRow = requireNativeComponent("LeanbackNativeRow");

const REQUEST_FOCUS_ACTION = 1;

const Row = React.forwardRef(
  ({ attributes, forbiddenFocusDirections, data, ...restOfProps }, ref) => {
    const rowRef = useRef();

    const attrs = {
      data,
      attributes: {
        width: attributes?.width || 513,
        height: attributes?.height || 176,
        hasTitle:
          attributes?.hasTitle !== undefined ? attributes.hasTitle : true,
        hasImageOnly:
          attributes?.hasImageOnly !== undefined
            ? attributes.hasImageOnly
            : false,
        hasContent:
          attributes?.hasContent !== undefined ? attributes.hasContent : true,
        hasIconRight:
          attributes?.hasIconRight !== undefined
            ? attributes.hasIconRight
            : false,
        hasIconLeft:
          attributes?.hasIconLeft !== undefined
            ? attributes.hasIconLeft
            : false,
        forbiddenFocusDirections:
          forbiddenFocusDirections && Array.isArray(forbiddenFocusDirections)
            ? forbiddenFocusDirections
            : [],
        focusedCardAlignment: attributes?.focusedCardAlignment || "left",
      },
    };

    useImperativeHandle(ref, () => ({
      requestFocus: () => {
        requestFocus();
      },
    }));

    const requestFocus = () => {
      const node = findNodeHandle(rowRef.current);
      UIManager.dispatchViewManagerCommand(node, REQUEST_FOCUS_ACTION, []);
    };

    return (
      <LeanbackNativeRow
        {...restOfProps}
        ref={rowRef}
        dataAndAttributes={attrs}
        onFocus={(event) => {
          const { item } = event.nativeEvent;
          if (restOfProps.onFocus) restOfProps.onFocus(JSON.parse(item));
        }}
        onPress={(event) => {
          const { item } = event.nativeEvent;
          if (restOfProps.onPress) restOfProps.onPress(JSON.parse(item));
        }}
        onDataIdsReady={(event) => {
          const { data: stringifiedData } = event.nativeEvent;
          if (restOfProps.onDataIdsReady) {
            const data = JSON.parse(stringifiedData);
            if (data.length) restOfProps.onDataIdsReady(data);
          }
        }}
      />
    );
  }
);

export default Row;
