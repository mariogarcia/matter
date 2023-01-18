FROM jupyter/base-notebook
USER root
# RUN apt-get update && apt-get install -y openjdk-11-jre
#COPY ./base-dependencies/ /root/.m2/repository/
COPY matter /tmp/matter/
WORKDIR /tmp/matter/

RUN /bin/bash -c "conda env create -n matter -f configuration.yml && . /opt/conda/etc/profile.d/conda.sh && conda activate matter && pip install -r requirements.txt --verbose && matter_kernel_groovy install --lab"

# Fix permissions as root
USER root
RUN fix-permissions /etc/jupyter/
RUN fix-permissions /opt/conda/envs/matter/share/jupyter/kernels/groovy
RUN fix-permissions /home/jovyan/.local/share/jupyter

# Switch back to jovyan to avoid accidental container runs as root
USER jovyan
WORKDIR $HOME