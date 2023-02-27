FROM jupyter/scipy-notebook:lab-3.5.3 as build
USER root
COPY . /tmp/matter/
WORKDIR /tmp/matter/
RUN npm install --global rimraf
RUN apt-get update && apt-get install -y openjdk-11-jdk-headless
RUN chmod +x build.sh
RUN ./build.sh
USER root
RUN fix-permissions /etc/jupyter/
#RUN fix-permissions /opt/conda/share/jupyter/kernels/matter
RUN fix-permissions /home/jovyan/.local/share/jupyter
#USER jovyan
WORKDIR $HOME