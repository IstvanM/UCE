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
package de.fhkn.in.uce.directconnection;

import java.util.Collections;
import java.util.Set;

import net.jcip.annotations.Immutable;
import de.fhkn.in.uce.plugininterface.NATSituation;
import de.fhkn.in.uce.plugininterface.NATTraversalTechniqueMetaData;
import de.fhkn.in.uce.plugininterface.util.NATTraversalTechniqueUtil;

/**
 * Implementation of {@link NATTraversalTechniqueMetaData} for
 * {@link Directconnection}.
 * 
 * @author Alexander Diener (aldiener@htwg-konstanz.de)
 * 
 */
@Immutable
public final class DirectconnectionMetaData implements NATTraversalTechniqueMetaData {
    private static final String RESOURCE_TRAVERSALED_SITUATIONS = "de/fhkn/in/uce/directconnection/traversal/traversaledNATSituations"; //$NON-NLS-1$
    private final String name = "DirectConnection"; //$NON-NLS-1$
    private final String version = "1.0"; //$NON-NLS-1$
    private final int connectionSetupTime = 3;
    private final int timoutInSeconds = 5;
    private final Set<NATSituation> traversaledNATSituations;
    private final NATTraversalTechniqueUtil util = NATTraversalTechniqueUtil.getInstance();

    public DirectconnectionMetaData() throws Exception {
        this.traversaledNATSituations = Collections.unmodifiableSet(this.util
                .parseNATSituations(RESOURCE_TRAVERSALED_SITUATIONS));
    }

    public DirectconnectionMetaData(final DirectconnectionMetaData toCopy) {
        this.traversaledNATSituations = toCopy.traversaledNATSituations;
    }

    public String getTraversalTechniqueName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public int getMinConnectionSetupTime() {
        return this.connectionSetupTime;
    }

    public int getMaxConnectionSetupTime() {
        return this.connectionSetupTime;
    }

    public boolean isFallbackTechnique() {
        return false;
    }

    public Set<NATSituation> getTraversaledNATSituations() {
        return Collections.unmodifiableSet(this.traversaledNATSituations);
    }

    public long getTimeout() {
        return this.timoutInSeconds * 1000;
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
        final DirectconnectionMetaData other = (DirectconnectionMetaData) obj;
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
}
