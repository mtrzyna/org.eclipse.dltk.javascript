/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.core.tests.contentassist;

import static org.eclipse.dltk.javascript.core.tests.contentassist.AbstractContentAssistTest.lastPositionInFile;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.codeassist.ISelectionRequestor;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.javascript.core.JavaScriptNature;

public class SelectionTests extends AbstractModelTests {

	public SelectionTests(String name) {
		super("org.eclipse.dltk.javascript.core.tests", name);
	}

	public static Suite suite() {
		return new Suite(SelectionTests.class);
	}

	@Override
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		setUpScriptProject("selection");
		waitUntilIndexesReady();
	}

	@Override
	public void tearDownSuite() throws Exception {
		deleteProject("selection");
		super.tearDownSuite();
	}

	private ISelectionEngine getEngine() {
		return DLTKLanguageManager
				.getSelectionEngine(JavaScriptNature.NATURE_ID);
	}

	private static class TestSelectionRequestor implements ISelectionRequestor {

		final List<IModelElement> elements = new ArrayList<IModelElement>();

		public TestSelectionRequestor(ISelectionEngine engine) {
			engine.setRequestor(this);
		}

		public void acceptForeignElement(Object object) {
		}

		public void acceptModelElement(IModelElement element) {
			this.elements.add(element);
		}

		public IModelElement[] toArray() {
			return elements.toArray(new IModelElement[elements.size()]);
		}

	}

	public void test1() throws ModelException {
		ISelectionEngine engine = getEngine();
		IModuleSource module = (IModuleSource) getSourceModule("selection",
				"src", "selection1.js");
		TestSelectionRequestor requestor = new TestSelectionRequestor(engine);
		engine.select(module, lastPositionInFile("test1()", module, false), 0);
		IModelElement[] elements = requestor.toArray();
		assertEquals(1, elements.length);
		final IMethod method = (IMethod) elements[0];
		final int offset = method.getSourceRange().getOffset();
		assertEquals("function", module.getSourceContents().substring(offset,
				offset + 8));
		final ISourceRange nameRange = method.getNameRange();
		assertEquals("test1", module.getSourceContents().substring(
				nameRange.getOffset(),
				nameRange.getOffset() + nameRange.getLength()));
	}

	public void test2() throws ModelException {
		ISelectionEngine engine = getEngine();
		IModuleSource module = (IModuleSource) getSourceModule("selection",
				"src", "selection1.js");
		TestSelectionRequestor requestor = new TestSelectionRequestor(engine);
		engine.select(module, lastPositionInFile("test2()", module, false), 0);
		IModelElement[] elements = requestor.toArray();
		assertEquals(1, elements.length);
		final IMethod method = (IMethod) elements[0];
		final int offset = method.getSourceRange().getOffset();
		assertEquals("function", module.getSourceContents().substring(offset,
				offset + 8));
		final ISourceRange nameRange = method.getNameRange();
		assertEquals("test2", module.getSourceContents().substring(
				nameRange.getOffset(),
				nameRange.getOffset() + nameRange.getLength()));
	}

	public void test3() throws ModelException {
		ISelectionEngine engine = getEngine();
		IModuleSource module = (IModuleSource) getSourceModule("selection",
				"src", "selection1.js");
		TestSelectionRequestor requestor = new TestSelectionRequestor(engine);
		engine.select(module, lastPositionInFile("test3()", module, false), 0);
		IModelElement[] elements = requestor.toArray();
		assertEquals(1, elements.length);
		final IMethod method = (IMethod) elements[0];
		final int offset = method.getSourceRange().getOffset();
		assertEquals("function", module.getSourceContents().substring(offset,
				offset + 8));
		final ISourceRange nameRange = method.getNameRange();
		assertEquals("test3", module.getSourceContents().substring(
				nameRange.getOffset(),
				nameRange.getOffset() + nameRange.getLength()));
	}

}