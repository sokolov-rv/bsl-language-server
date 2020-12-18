/*
 * This file is a part of BSL Language Server.
 *
 * Copyright © 2018-2020
 * Alexey Sosnoviy <labotamy@gmail.com>, Nikita Gryzlov <nixel2007@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * BSL Language Server is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * BSL Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BSL Language Server.
 */
package com.github._1c_syntax.bsl.languageserver.references;

import com.github._1c_syntax.bsl.languageserver.context.callee.CalleeStorage;
import com.github._1c_syntax.bsl.languageserver.context.symbol.Symbol;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

/**
 * Реализация поискового движка на основе поиска в {@link CalleeStorage}.
 */
@Component
@RequiredArgsConstructor
public class ReferencesStorageReferenceFinder implements ReferenceFinder {

  private final CalleeStorage calleeStorage;

  @Override
  public Optional<Pair<Symbol, Range>> findReference(URI uri, Position position) {
    return calleeStorage.getCalledMethodSymbol(uri, position).map(
      pair -> Pair.of(pair.getLeft(), pair.getRight())
    );
  }
}