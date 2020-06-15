"use strict";
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
// https://jira.sonarsource.com/browse/RSPEC-4619
Object.defineProperty(exports, "__esModule", { value: true });
const isRequiredParserServices_1 = require("../utils/isRequiredParserServices");
const message = 'Use "indexOf" or "includes" (available from ES2016) instead.';
exports.rule = {
    create(context) {
        const services = context.parserServices;
        if (isRequiredParserServices_1.isRequiredParserServices(services)) {
            const checker = services.program.getTypeChecker();
            function isArray(node) {
                const typ = checker.getTypeAtLocation(services.esTreeNodeToTSNodeMap.get(node));
                return typ.symbol && typ.symbol.name === 'Array';
            }
            return {
                "BinaryExpression[operator='in']": function (node) {
                    if (isArray(node.right)) {
                        context.report({
                            message,
                            node,
                        });
                    }
                },
            };
        }
        return {};
    },
};
//# sourceMappingURL=no-in-misuse.js.map