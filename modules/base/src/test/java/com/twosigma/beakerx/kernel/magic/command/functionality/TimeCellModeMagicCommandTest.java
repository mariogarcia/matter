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
package com.twosigma.beakerx.kernel.magic.command.functionality;

import com.twosigma.beakerx.KernelTest;
import com.twosigma.beakerx.evaluator.EvaluatorResultTestWatcher;
import com.twosigma.beakerx.evaluator.EvaluatorTest;
import com.twosigma.beakerx.evaluator.SimpleEvaluationObjectFactory;
import com.twosigma.beakerx.kernel.Code;
import com.twosigma.beakerx.kernel.magic.command.CodeFactory;
import com.twosigma.beakerx.kernel.magic.command.MagicCommandConfigurationMock;
import com.twosigma.beakerx.kernel.msg.MessageCreator;
import com.twosigma.beakerx.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.twosigma.beakerx.MessageFactorTest.commMsg;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeCellModeMagicCommandTest {
  private KernelTest kernel;
  private EvaluatorTest evaluator;
  private CodeFactory codeFactory;

  @Before
  public void setUp() throws Exception {
    this.evaluator = new EvaluatorTest();
    this.kernel = new KernelTest("id2", evaluator, MessageCreator.get(), new MagicCommandConfigurationMock());
    codeFactory = new CodeFactory(MessageCreator.get(), SimpleEvaluationObjectFactory.get());
  }

  @After
  public void tearDown() throws Exception {
    kernel.exit();
  }

  @Test
  public void timeInCellMode() {
    //given
    String allCode = "" +
            "%%time \n" +
            "a = [10,20,30,40,50]; \n" +
            "a= a.sum(); \n" +
            "a*10;";
    Code code = codeFactory.create(allCode, commMsg(), kernel);
    //when
    code.execute(kernel, 1);
    //then
    List<Message> std = EvaluatorResultTestWatcher.getStdouts(kernel.getPublishedMessages());
    String text = (String) std.get(0).getContent().get("text");
    assertThat(text).contains("CPU times:");
  }
}