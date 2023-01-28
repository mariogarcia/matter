FROM jupyter/base-notebook as build
USER root
COPY matter /tmp/matter/
WORKDIR /tmp/matter/
RUN apt-get update && apt-get install -y openjdk-11-jdk-headless
RUN /bin/bash -c "conda env create -n beakerx --file configuration.yml && . /opt/conda/etc/profile.d/conda.sh && conda activate beakerx && pip install -r requirements.txt --verbose && beakerx_kernel_groovy install"
USER root
RUN fix-permissions /etc/jupyter/
#RUN fix-permissions /opt/conda/share/jupyter/kernels/matter
RUN fix-permissions /home/jovyan/.local/share/jupyter
#USER jovyan
WORKDIR $HOME