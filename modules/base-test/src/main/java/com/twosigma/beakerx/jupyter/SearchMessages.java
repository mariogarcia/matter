/*
 *  Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.twosigma.beakerx.jupyter;

import com.twosigma.beakerx.kernel.comm.Comm;
import com.twosigma.beakerx.kernel.msg.JupyterMessages;
import com.twosigma.beakerx.widget.LayoutInfo;
import com.twosigma.beakerx.message.Message;
import com.twosigma.beakerx.widget.WidgetInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchMessages {

  public static List<Message> getListLayout(List<Message> messages){
    return getListByDataAttr(messages, WidgetInfo.VIEW_NAME, LayoutInfo.VIEW_NAME_VALUE);
  }

  public static Message getLayoutForWidget(List<Message> messages, Message widget){
    Map map = (Map) ((Map) widget.getContent().get(Comm.DATA)).get(Comm.STATE);
    if(map == null || map.get(LayoutInfo.LAYOUT) == null) return null;
    String id = ((String) map.get(LayoutInfo.LAYOUT)).replace(LayoutInfo.IPY_MODEL, "");
    return getMessageByCommId(messages, id);
  }

  public static List<Message> getListWidgetsByViewName(List<Message> messages, String viewNameValue){
    return getListByDataAttr(messages, WidgetInfo.VIEW_NAME, viewNameValue);
  }

  public static List<Message> getListWidgetsByModelName(List<Message> messages, String modelNameValue){
    return getListByDataAttr(messages, WidgetInfo.MODEL_NAME, modelNameValue);
  }

  public static List<Message> getListByDataAttr(List<Message> messages, String key, String value){
    return messages.stream()
        .filter(m -> {
          Map map;
          if (key.equals(Comm.METHOD)) {
            map = (Map) m.getContent().get(Comm.DATA);
          } else {
            map = (Map) ((Map)m.getContent().get(Comm.DATA)).get(Comm.STATE);
          }

          return map != null && map.containsKey(key) && value.equals(map.get(key));
        })
        .collect(Collectors.toList());
  }

  public static Message getMessageByCommId(List<Message> messages, String id){
    return messages.stream()
        .filter(m -> id.equals((String) m.getContent().get(Comm.COMM_ID)))
        .findFirst()
        .get();
  }

  public static List<Message> getListMessagesByType(List<Message> messages, JupyterMessages type){
    return messages.stream()
        .filter(m -> m.getHeader() != null
                && m.getHeader().getType() != null
                && m.getHeader().getType().equalsIgnoreCase(type.toString())
        )
        .collect(Collectors.toList());
  }

}
