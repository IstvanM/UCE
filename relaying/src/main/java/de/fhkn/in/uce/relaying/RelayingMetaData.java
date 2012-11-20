/*
 * Copyright (c) 2012 Alexander Diener,
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhkn.in.uce.relaying;

import java.util.Collections;
import java.util.Set;

import net.jcip.annotations.Immutable;
import de.fhkn.in.uce.plugininterface.NATSituation;
import de.fhkn.in.uce.plugininterface.NATTraversalTechniqueMetaData;
import de.fhkn.in.uce.plugininterface.util.NATTraversalTechniqueUtil;
import de.fhkn.in.uce.relaying.message.RelayingAttribute;
import de.fhkn.in.uce.stun.attribute.Attribute;

/**
 * Implementation of {@link NATTraversalTechniqueMetaData} for {@link Relaying}.
 * 
 * @author Alexander Diener (aldiener@htwg-konstanz.de)
 * 
 */
@Immutable
public final class RelayingMetaData implements NATTraversalTechniqueMetaData {
    private static final String RESOURCE_TRAVERSALED_SITUATIONS = "de/fhkn/in/uce/relay/traversal/traversaledNATSituations"; //$NON-NLS-1$
    private final String name = "Relaying"; //$NON-NLS-1$
    private final String version = "1.0"; //$NON-NLS-1$
    private final int connectionSetupTime = 6;
    private final int timeoutInSeconds = 30;
    private final Set<NATSituation> traversaledNATSituations;
    private final NATTraversalTechniqueUtil util = NATTraversalTechniqueUtil.getInstance();

    public RelayingMetaData() throws Exception {
        this.traversaledNATSituations = Collections.unmodifiableSet(this.util
                .parseNATSituations(RESOURCE_TRAVERSALED_SITUATIONS));
    }

    public RelayingMetaData(final RelayingMetaData toCopy) {
        this.traversaledNATSituations = toCopy.traversaledNATSituations;
    }

    @Override
    public String getTraversalTechniqueName() {
        return this.name;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public int getMinConnectionSetupTime() {
        return this.connectionSetupTime;
    }

    @Override
    public int getMaxConnectionSetupTime() {
        return this.connectionSetupTime;
    }

    @Override
    public boolean isFallbackTechnique() {
        return true;
    }

    @Override
    public Set<NATSituation> getTraversaledNATSituations() {
        // return an empty set because this traversal technique is a fallback
        // technique
        // return Collections.emptySet();
        // return all possible nat situation
        // final NATTraversalTechniqueUtil util =
        // NATTraversalTechniqueUtil.getInstance();
        // return util.getAllPossibleNATSituations();
        return Collections.unmodifiableSet(this.traversaledNATSituations);
    }

    @Override
    public long getTimeout() {
        return this.timeoutInSeconds * 1000;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.connectionSetupTime;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final RelayingMetaData other = (RelayingMetaData) obj;
        if (this.connectionSetupTime != other.connectionSetupTime) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!this.version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @Override
    public Attribute getAttribute() {
        return new RelayingAttribute();
    }
}
